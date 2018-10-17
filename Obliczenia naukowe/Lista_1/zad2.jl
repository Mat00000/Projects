#=
#   @author Mateusz Laskowski
=#

################# TWIERDZENIE KAHANA #################
println("epsilon = 3(4/3-1)-1");
println();

# Float16
println("Float16")
epsilon = Float16(Float16(3.0 * (Float16(4.0/3.0) - Float16(1.0))) - Float16(1.0));
print("wynik: ")
println(epsilon);
print("eps: ");
println(eps(Float16));
println();

# Float32
println("Float32")
epsilon = Float32(Float32(3.0 * (Float32(4.0/3.0) - Float32(1.0))) - Float32(1.0));
print("wynik: ")
println(epsilon);
print("eps: ");
println(eps(Float32));
println();

# Float64
println("Float64")
epsilon = Float64(Float64(3.0 * (Float64(4.0/3.0) - Float64(1.0))) - Float64(1.0));
print("wynik: ")
println(epsilon);
print("eps: ");
println(eps(Float64));
