include("zad1.jl");
using MyModule

x = [-1.0, 0.0, 1.0, 2.0]
f = [-1.0, 0.0, -1.0, 2.0]

tab = MyModule.ilorazyRoznicowe(x, f)


println(MyModule.naturalna(x, tab))

