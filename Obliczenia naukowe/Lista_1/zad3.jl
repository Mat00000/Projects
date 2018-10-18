#=
#   @author Mateusz Laskowski
=#

################# ROZMIESZCZENIE W PRZEDZIALE [p1,p2] #################
# Przedział [1,2]
p1 = Float64(1.0);
p2 = Float64(2.0);
delta = Float64(nextfloat(p1) - Float64(p1));
k = Float64(1.0);                                                   # zmienna, która będzie miała wartości 1,2,...,(2^52)-1
x = Float64(1.0);                                                   # zmienna przechowująca liczbę zmiennopozycyjną
while (Float64(x) <= Float64(p2))                                   # dopuki x jest w przedziale [1,2]
    x = Float64(x + k * delta);
    if x - prevfloat(x) != delta
        println("Delta różna od ", delta, " dla x = ", x, " => delta' = ", x - prevfloat(x))
    end
end