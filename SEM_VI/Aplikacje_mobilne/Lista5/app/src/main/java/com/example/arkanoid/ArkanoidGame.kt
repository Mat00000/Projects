package com.example.arkanoid

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.RectF
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

import android.R.attr.bottom
import android.R.attr.left
import android.R.attr.right
import android.R.attr.top
import com.example.arkanoid.Ball
import com.example.arkanoid.Brick
import com.example.arkanoid.Paddle




class ArkanoidGame : Activity() {

    // gameView will be the view of the game
    // It will also hold the logic of the game
    // and respond to screen touches as well
    private lateinit var breakoutView: BreakoutView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize gameView and set it as the view
        breakoutView = BreakoutView(this)
        setContentView(breakoutView)

    }

    // Here is our implementation of BreakoutView
    // It is an inner class.
    // Note how the final closing curly brace }
    // is inside the BreakoutGame class

    // Notice we implement runnable so we have
    // A thread and can override the run method.
    internal inner class BreakoutView// When the we initialize (call new()) on gameView
    // This special constructor method runs
        (context: Context) : SurfaceView(context), Runnable {

        // This is our thread
        var gameThread: Thread? = null

        // This is new. We need a SurfaceHolder
        // When we use Paint and Canvas in a thread
        // We will see it in action in the draw method soon.
        var ourHolder: SurfaceHolder

        // A boolean which we will set and unset
        // when the game is running- or not.
        @Volatile
        var playing: Boolean = false

        // Game is paused at the start
        var paused = true

        // A Canvas and a Paint object
        lateinit var canvas: Canvas
        var paint: Paint

        // This variable tracks the game frame rate
        var fps: Long = 0

        // This is used to help calculate the fps
        private var timeThisFrame: Long = 0

        // The size of the screen in pixels
        var screenX: Int = 0
        var screenY: Int = 0

        // The players paddle
        var paddle: Paddle

        // A ball
        var ball: Ball

        // Up to 200 bricks
        var bricks = arrayOfNulls<Brick>(200)
        var numBricks = 0

        // For sound FX
//        var soundPool: SoundPool
        var beep1ID = -1
        var beep2ID = -1
        var beep3ID = -1
        var loseLifeID = -1
        var explodeID = -1

        // The score
        var score = 0

        // Lives
        var lives = 3

        init {

            // Initialize ourHolder and paint objects
            ourHolder = holder
            paint = Paint()

            // Get a Display object to access screen details
            val display = windowManager.defaultDisplay
            // Load the resolution into a Point object
            val size = Point()
            display.getSize(size)

            screenX = size.x
            screenY = size.y

            paddle = Paddle(screenX, screenY)

            // Create a ball
            ball = Ball(screenX, screenY)

            // Load the sounds

//            // This SoundPool is deprecated but don't worry
//            soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
//
//            try {
//                // Create objects of the 2 required classes
//                val assetManager = context.assets
//                var descriptor: AssetFileDescriptor
//
//                // Load our fx in memory ready for use
//                descriptor = assetManager.openFd("beep1.ogg")
//                beep1ID = soundPool.load(descriptor, 0)
//
//                descriptor = assetManager.openFd("beep2.ogg")
//                beep2ID = soundPool.load(descriptor, 0)
//
//                descriptor = assetManager.openFd("beep3.ogg")
//                beep3ID = soundPool.load(descriptor, 0)
//
//                descriptor = assetManager.openFd("loseLife.ogg")
//                loseLifeID = soundPool.load(descriptor, 0)
//
//                descriptor = assetManager.openFd("explode.ogg")
//                explodeID = soundPool.load(descriptor, 0)
//
//            } catch (e: IOException) {
//                // Print an error message to the console
//                Log.e("error", "failed to load sound files")
//            }

            createBricksAndRestart()

        }// The next line of code asks the
        // SurfaceView class to set up our object.
        // How kind.

        fun createBricksAndRestart() {

            // Put the ball back to the start
            ball.reset(screenX, screenY)

            val brickWidth = screenX / 8
            val brickHeight = screenY / 10

            // Build a wall of bricks
            numBricks = 0
            for (column in 0..7) {
                for (row in 0..2) {
                    bricks[numBricks] = Brick(row, column, brickWidth, brickHeight)
                    numBricks++
                }
            }
            // if game over reset scores and lives
            if (lives == 0) {
                score = 0
                lives = 3
            }
        }

        override fun run() {
            while (playing) {
                // Capture the current time in milliseconds in startFrameTime
                val startFrameTime = System.currentTimeMillis()
                // Update the frame
                if (!paused) {
                    update()
                }
                // Draw the frame
                draw()
                // Calculate the fps this frame
                // We can then use the result to
                // time animations and more.
                timeThisFrame = System.currentTimeMillis() - startFrameTime
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame
                }

            }

        }

        // Everything that needs to be updated goes in here
        // Movement, collision detection etc.
        fun update() {

            // Move the paddle if required
            paddle.update(fps)

            ball.update(fps)

            // Check for ball colliding with a brick
            for (i in 0 until numBricks) {
                if (bricks[i]!!.getVisibility()) {
                    if (RectF.intersects(bricks[i]!!.getRect(), ball.getRectt())) {
                        bricks[i]!!.setInvisible()
                        ball.reverseYVelocity()
                        score += 10
//                        soundPool.play(explodeID, 1f, 1f, 0, 0, 1f)
                    }
                }
            }
            // Check for ball colliding with paddle
            if (RectF.intersects(paddle.getRect(), ball.getRectt())) {
                ball.setRandomXVelocity()
                ball.reverseYVelocity()
                ball.clearObstacleY(paddle.getRect().top - 2)
//                soundPool.play(beep1ID, 1f, 1f, 0, 0, 1f)
            }
            // Bounce the ball back when it hits the bottom of screen
            if (ball.getRectt().bottom > screenY) {
                ball.reverseYVelocity()
                ball.clearObstacleY(screenY - 2.0F)

                // Lose a life
                lives--
//                soundPool.play(loseLifeID, 1f, 1f, 0, 0, 1f)

                if (lives == 0) {
                    paused = true
                    createBricksAndRestart()
                }
            }

            // Bounce the ball back when it hits the top of screen
            if (ball.getRectt().top < 0) {
                ball.reverseYVelocity()
                ball.clearObstacleY(12.0F)

//                soundPool.play(beep2ID, 1f, 1f, 0, 0, 1f)
            }

            // If the ball hits left wall bounce
            if (ball.getRectt().left < 0) {
                ball.reverseXVelocity()
                ball.clearObstacleX(2.0F)
//                soundPool.play(beep3ID, 1f, 1f, 0, 0, 1f)
            }

            // If the ball hits right wall bounce
            if (ball.getRectt().right > screenX - 10) {

                ball.reverseXVelocity()
                ball.clearObstacleX(screenX - 22.0F)

//                soundPool.play(beep3ID, 1f, 1f, 0, 0, 1f)
            }

            // Pause if cleared screen
            if (score == numBricks * 10) {
                paused = true
                createBricksAndRestart()
            }

        }

        // Draw the newly updated scene
        fun draw() {

            // Make sure our drawing surface is valid or we crash
            if (ourHolder.surface.isValid) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas()

                // Draw the background color
                canvas.drawColor(Color.argb(255, 26, 128, 182))

                // Choose the brush color for drawing
                paint.color = Color.argb(255, 255, 255, 255)

                // Draw the paddle
                canvas.drawRect(paddle.getRect(), paint)

                // Draw the ball
                canvas.drawRect(ball.getRectt(), paint)

                // Change the brush color for drawing
                paint.color = Color.argb(255, 249, 129, 0)

                // Draw the bricks if visible
                for (i in 0 until numBricks) {
                    if (bricks[i]!!.getVisibility()) {
                        canvas.drawRect(bricks[i]!!.getRect(), paint)
                    }
                }

                // Choose the brush color for drawing
                paint.color = Color.argb(255, 255, 255, 255)

                // Draw the score
                paint.textSize = 40f
                canvas.drawText("Score: $score   Lives: $lives", 10f, 50f, paint)

                // Has the player cleared the screen?
                if (score == numBricks * 10) {
                    paint.textSize = 90f
                    canvas.drawText("YOU HAVE WON!", 10f, (screenY / 2).toFloat(), paint)
                }

                // Has the player lost?
                if (lives <= 0) {
                    paint.textSize = 90f
                    canvas.drawText("YOU HAVE LOST!", 10f, (screenY / 2).toFloat(), paint)
                }

                // Draw everything to the screen
                ourHolder.unlockCanvasAndPost(canvas)
            }
        }

        // If SimpleGameEngine Activity is paused/stopped
        // shutdown our thread.
        fun pause() {
            playing = false
            try {
                gameThread!!.join()
            } catch (e: InterruptedException) {
                Log.e("Error:", "joining thread")
            }

        }

        // If SimpleGameEngine Activity is started then
        // start our thread.
        fun resume() {
            playing = true
            gameThread = Thread(this)
            gameThread!!.start()
        }

        // The SurfaceView class implements onTouchListener
        // So we can override this method and detect screen touches.
        override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
            when (motionEvent.action and MotionEvent.ACTION_MASK) {
                // Player has touched the screen
                MotionEvent.ACTION_DOWN -> {
                    paused = false
                    if (motionEvent.x > screenX / 2) {

                        paddle.setMovementState(paddle.RIGHT)
                    } else {
                        paddle.setMovementState(paddle.LEFT)
                    }
                }

                // Player has removed finger from screen
                MotionEvent.ACTION_UP ->

                    paddle.setMovementState(paddle.STOPPED)
            }

            return true
        }

    }
    // This is the end of our BreakoutView inner class

    // This method executes when the player starts the game
    override fun onResume() {
        super.onResume()

        // Tell the gameView resume method to execute
        breakoutView.resume()
    }

    // This method executes when the player quits the game
    override fun onPause() {
        super.onPause()

        // Tell the gameView pause method to execute
        breakoutView.pause()
    }

}
// This is the end of the BreakoutGame class