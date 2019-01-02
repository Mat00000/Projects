#=
#   @author Mateusz Laskowski
=#
################# TESTING RESULTS OF METHODS #################
include("module.jl")

using gaussian
using Base.Test

function testGaussiannElimination()
    # tests for matrix 16
    Matrix, _n, l = gaussian.readMatrix("dane/Dane_16/A.txt")
    V, n = gaussian.readVector("dane/Dane_16/b.txt")
    @time x, error = gaussian.gaussianElimination(Matrix, V, n, l)
    @time x, error = gaussian.gaussianElimination(Matrix, V, n, l)
    println("gaussianElimination_16: \n\terror -> ", error)
    gaussian.writeVector(x, "dane/tests/vector_16.txt")
    for i in 1:n
        @test x[i] ≈ 1.0
    end

    @time x, error = gaussian.gaussianEliminationWithPivot(Matrix, V, n, l)
    @time x, error = gaussian.gaussianEliminationWithPivot(Matrix, V, n, l)
    println("gaussianEliminationWithPivot_16: \n\terror -> ", error)
    gaussian.writeVector(x, "dane/tests/vectorWithPivot_16.txt")
    for i in 1:n
        @test x[i] ≈ 1.0
    end

    # test for matrix 10000
    Matrix, _n, l = gaussian.readMatrix("dane/Dane_10000/A.txt")
    V, n = gaussian.readVector("dane/Dane_10000/b.txt")
    @time x, error = gaussian.gaussianElimination(Matrix, V, n, l)
    @time x, error = gaussian.gaussianElimination(Matrix, V, n, l)
    println("gaussianElimination_10000: \n\terror -> ", error)
    gaussian.writeVector(x, "dane/tests/vector_10000.txt")
    for i in 1:n
        @test x[i] ≈ 1.0
    end

    @time x, error = gaussian.gaussianEliminationWithPivot(Matrix, V, n, l)
    @time x, error = gaussian.gaussianEliminationWithPivot(Matrix, V, n, l)
    println("gaussianEliminationWithPivot_10000: \n\terror -> ", error)
    gaussian.writeVector(x, "dane/tests/vectorWithPivot_10000.txt")
    for i in 1:n
        @test x[i] ≈ 1.0
    end

    # test for matrix 50000
    Matrix, _n, l = gaussian.readMatrix("dane/Dane_50000/A.txt")
    V, n = gaussian.readVector("dane/Dane_50000/b.txt")
    @time x, error = gaussian.gaussianElimination(Matrix, V, n, l)
    @time x, error = gaussian.gaussianElimination(Matrix, V, n, l)
    println("gaussianElimination_50000: \n\terror -> ", error)
    gaussian.writeVector(x, "dane/tests/vector_50000.txt")
    for i in 1:n
        @test x[i] ≈ 1.0
    end

    @time x, error = gaussian.gaussianEliminationWithPivot(Matrix, V, n, l)
    @time x, error = gaussian.gaussianEliminationWithPivot(Matrix, V, n, l)
    println("gaussianEliminationWithPivot_50000: \n\terror -> ", error)
    gaussian.writeVector(x, "dane/tests/vectorWithPivot_50000.txt")
    for i in 1:n
        @test x[i] ≈ 1.0
    end
end

function testDistributionLU(test::Bool)
    # test for matrix 16
    Matrix, n, l = gaussian.readMatrix("dane/Dane_16/A.txt")
    @time (L, U), error = gaussian.distributionLU(Matrix, n, l)
    @time (L, U), error = gaussian.distributionLU(Matrix, n, l)
    println("distributionLU_16: \n\terror -> ", error)
    distributionMatrix = L * U

    # test distributionMatrix
    if(test)
        for i in 1:n
            for j in 1:n
                # @test Matrix[i, j] ≈ distributionMatrix[i, j]
                println("i = ", i, " j = ", j)
                print(Matrix[i, j], "\t")
                println(distributionMatrix[i, j])
            end
        end
    end
            
    @time (L, U), error = gaussian.distributionLUWithPivot(Matrix, n, l)
    @time (L, U), error = gaussian.distributionLUWithPivot(Matrix, n, l)
    println("distributionLUWithPivot_16: \n\terror -> ", error)
    distributionMatrixWithPivot = L * U

    # test distributionMatrixWithPivot
    if(test)
        for i in 1:n
            for j in 1:n
                # @test Matrix[i, j] ≈ distributionMatrixWithPivot[i, j]
                println("i = ", i, " j = ", j)
                print(Matrix[i, j], "\t")
                println(distributionMatrixWithPivot[i, j])
            end
        end
    end

    # test for matrix 10000
    Matrix, n, l = gaussian.readMatrix("dane/Dane_10000/A.txt")
    @time (L, U), error = gaussian.distributionLU(Matrix, n, l)
    @time (L, U), error = gaussian.distributionLU(Matrix, n, l)
    println("distributionLU_10000: \n\terror -> ", error)
    distributionMatrix = L * U

    # test distributionMatrix
    if(test)
        for i in 1:n
            for j in 1:n
                # @test Matrix[i, j] ≈ distributionMatrix[i, j]
                println("i = ", i, " j = ", j)
                print(Matrix[i, j], "\t")
                println(distributionMatrix[i, j])
            end
        end
    end
        
    @time (L, U), error = gaussian.distributionLUWithPivot(Matrix, n, l)
    @time (L, U), error = gaussian.distributionLUWithPivot(Matrix, n, l)
    println("distributionLUWithPivot_10000: \n\terror -> ", error)
    distributionMatrixWithPivot = L * U

    # test distributionMatrixWithPivot
    if(test)
        for i in 1:n
            for j in 1:n
                # @test Matrix[i, j] ≈ distributionMatrixWithPivot[i, j]
                println("i = ", i, " j = ", j)
                print(Matrix[i, j], "\t")
                println(distributionMatrixWithPivot[i, j])
            end
        end
    end

    # test for matrix 50000
    Matrix, n, l = gaussian.readMatrix("dane/Dane_50000/A.txt")
    @time (L, U), error = gaussian.distributionLU(Matrix, n, l)
    # @time (L, U), error = gaussian.distributionLU(Matrix, n, l)
    println("distributionLU_50000: \n\terror -> ", error)
    distributionMatrix = L * U

    # test distributionMatrix
    if(test)
        for i in 1:n
            for j in 1:n
                # @test Matrix[i, j] ≈ distributionMatrix[i, j]
                println("i = ", i, " j = ", j)
                print(Matrix[i, j], "\t")
                println(distributionMatrix[i, j])
            end
        end
    end
            
    @time (L, U), error = gaussian.distributionLUWithPivot(Matrix, n, l)
    # @time (L, U), error = gaussian.distributionLUWithPivot(Matrix, n, l)
    println("distributionLUWithPivot_50000: \n\terror -> ", error)
    distributionMatrixWithPivot = L * U

    # test distributionMatrixWithPivot
    if(test)
        for i in 1:n
            for j in 1:n
                # @test Matrix[i, j] ≈ distributionMatrixWithPivot[i, j]
                println("i = ", i, " j = ", j)
                print(Matrix[i, j], "\t")
                println(distributionMatrixWithPivot[i, j])
            end
        end
    end
end

function testSolutionFromLUMatrices()
    # tests for matrix 16
    Matrix, _n, l = gaussian.readMatrix("dane/Dane_16/A.txt")
    V, n = gaussian.readVector("dane/Dane_16/b.txt")
    (L, U), error = gaussian.distributionLU(Matrix, n, l)
    @time x = gaussian.solutionFromLUMatrices(L, U, V, n, l)
    @time x = gaussian.solutionFromLUMatrices(L, U, V, n, l)
    gaussian.writeVector(x, "dane/tests/vectorFromLU_16.txt")
    for i in 1:n
        @test x[i] ≈ 1.0
    end

    # tests for matrix 10000
    Matrix, _n, l = gaussian.readMatrix("dane/Dane_10000/A.txt")
    V, n = gaussian.readVector("dane/Dane_10000/b.txt")
    (L, U), error = gaussian.distributionLU(Matrix, n, l)
    @time x = gaussian.solutionFromLUMatrices(L, U, V, n, l)
    @time x = gaussian.solutionFromLUMatrices(L, U, V, n, l)
    gaussian.writeVector(x, "dane/tests/vectorFromLU_10000.txt")
    for i in 1:n
        @test x[i] ≈ 1.0
    end

    # tests for matrix 50000
    Matrix, _n, l = gaussian.readMatrix("dane/Dane_50000/A.txt")
    V, n = gaussian.readVector("dane/Dane_50000/b.txt")
    (L, U), error = gaussian.distributionLU(Matrix, n, l)
    @time x = gaussian.solutionFromLUMatrices(L, U, V, n, l)
    @time x = gaussian.solutionFromLUMatrices(L, U, V, n, l)
    gaussian.writeVector(x, "dane/tests/vectorFromLU_50000.txt")
    for i in 1:n
        @test x[i] ≈ 1.0
    end
end

# tests for tast 1
# testGaussiannElimination()
# tests for task 2
testDistributionLU(false)
# test for task 3
testSolutionFromLUMatrices()
