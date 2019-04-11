factorial n = if (n < 2) then 1 else n * factorial (n-1)
zero n = if ( n `mod` 10 == 0) then (1 + zero ( n `div` 10)) else 0
zeros n = zero (factorial n)