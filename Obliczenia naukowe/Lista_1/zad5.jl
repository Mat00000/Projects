#=
#   @author Mateusz Laskowski
=#

################# ILOCZYN SKALARNY DWÓCH WEKTORÓW #################

x = [Float32(2.718281828), Float32(-3.141592654), Float32(1.414213562), Float32(0.5772156649), Float32(0.3010299957)];              # tablica wektorów współ. X 
y = [Float32(1486.2497), Float32(878366.9879), Float32(-22.37492), Float32(4773714.647), Float32(0.000185049)];                     # tablica wektorów współ. Y
n = 5;                                                                                          

################# FLOAT32 #################
println("-- FLOAT32 --");
# Podpunkt A
S = Float32(0.0);
for i = 1 : n
    S = Float32(S) + Float32(x[i] * y[i]);
    # println("S w petli ", S);
end
println("WYNIK w przód  S = ", S);

# Podpunkt B
S = Float32(0.0);
i = 5;
while (i > 0 )
    S = Float32(S) + Float32(x[i] * y[i]);
    # println("S w petli ", S);
    i = i - 1;
end
println("WYNIK w tył    S = ", S);

# Podpunkt C
S = Float32(0.0);
Array = zeros(Float32, n);
for i = 1 : n
    Array[i] = Float32(x[i] * y[i]); 
end

sort!(Array);
# println(Array);

i = 5;
S_plus = Float32(0.0);
S_minus = Float32(0.0);
while (i > 0)
    if (Float32(Array[i]) > Float32(0.0))
        S_plus = Float32(S_plus) + Float32(Array[i]);
        # println("suma_p ", S_plus);
    elseif (Float32(Array[i]) < Float32(0.0))
        S_minus = Float32(S_minus) + Float32(Array[i]);
        # println("suma_m ", S_minus);
    end
    i = i - 1;
end

S = Float32(S_plus + S_minus);
println("WYNIK w podp.C S = ", S);

# Podpunkt D
S = Float32(0.0);
Array = zeros(Float32, n);
for i = 1 : n
    Array[i] = Float32(x[i] * y[i]);
end

sort!(Array);
# println(Array);

i = 1
S_plus = Float32(0.0);
S_minus = Float32(0.0);
while (i <= n)
    if (Float32(Array[i]) < Float32(0.0))
        S_minus = Float32(S_minus) + Float32(Array[i]);
    elseif (Float32(Array[i]) > Float32(0.0))
        S_plus = Float32(S_plus) + Float32(Array[i]);
    end
    i = i + 1;
end

S = Float32(S_minus + S_plus);
println("WYNIK w podp.D S = ", S);

################# FLOAT64 #################
x = [Float64(2.718281828), Float64(-3.141592654), Float64(1.414213562), Float64(0.5772156649), Float64(0.3010299957)];              # tablica wektorów współ. X 
y = [Float64(1486.2497), Float64(878366.9879), Float64(-22.37492), Float64(4773714.647), Float64(0.000185049)];  
println("-- FLOAT64 --");
# Podpunkt A
S = Float64(0.0);
for i = 1 : n
    S = Float64(S) + Float64(x[i] * y[i]);
    # println("S w petli ", S);
end
println("WYNIK w przód  S = ", S);

# Podpunkt B
S = Float64(0.0);
i = 5;
while (i > 0 )
    S = Float64(S) + Float64(x[i] * y[i]);
    # println("S w petli ", S);
    i = i - 1;
end
println("WYNIK w tył    S = ", S);

# Podpunkt C
S = Float64(0.0);
Array = zeros(Float64, n);
for i = 1 : n
    Array[i] = Float64(x[i] * y[i]); 
end

sort!(Array);
# println(Array);

i = 5;
S_plus = Float64(0.0);
S_minus = Float64(0.0);
while (i > 0)
    if (Float64(Array[i]) > Float64(0.0))
        S_plus = Float64(S_plus) + Float64(Array[i]);
        # println("suma_p ", S_plus);
    elseif (Float64(Array[i]) < Float64(0.0))
        S_minus = Float64(S_minus) + Float64(Array[i]);
        # println("suma_m ", S_minus);
    end
    i = i - 1;
end

S = Float64(S_plus + S_minus);
println("WYNIK w podp.C S = ", S);

# Podpunkt D
S = Float64(0.0);
Array = zeros(Float64, n);
for i = 1 : n
    Array[i] = Float64(x[i] * y[i]);
end

sort!(Array);
# println(Array);

i = 1
S_plus = Float64(0.0);
S_minus = Float64(0.0);
while (i <= n)
    if (Float64(Array[i]) < Float64(0.0))
        S_minus = Float64(S_minus) + Float64(Array[i]);
    elseif (Float64(Array[i]) > Float64(0.0))
        S_plus = Float64(S_plus) + Float64(Array[i]);
    end
    i = i + 1;
end

S = Float64(S_minus + S_plus);
println("WYNIK w podp.D S = ", S);