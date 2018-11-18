#=
#   @author Mateusz Laskowski
=#
################# METHODS TO DO #################
################# ZAD1/ZAD2/ZAD3 #################

module calculationMethods
export mbisekcji
export mstycznych
export msiecznych

function mbisekcji(f, a::Float64, b::Float64, delta::Float64, epsilon::Float64)
    u = f(a)
    w = f(b)
    e = b - a

    if (sign(u) == sign(w))
        return (a, b, 0, 1)
    end
    while(true)
        e = e / 2
        r = a + e
        v = f(r)

        if (abs(e) < delta || abs(v) < epsilon)
            return (r, v, 1, 0)
        end
        if (sign(v) != sign(u))
            b = r
            w = v
        else
            a = r
            u = v
        end
    end
end

function mstycznych(f, pf, x0::Float64, delta::Float64, epsilon::Float64, maxit::Int)
    v = f(x0)
    for k = 1:maxit
        if (abs(pf(x0)) < epsilon)
            return (x0, pf(x0), 0, 2)
        end 

        x1 = x0 - (v / (pf(x0)))
        v = f(x1)

        if abs(x1 - x0) < delta || abs(v) < epsilon
            return (x1, v, k, 0)
        end

        x0 = x1;
    end
    return (x0, f(x0), maxit, 1)  
end

function msiecznych(f, a::Float64, b::Float64, delta::Float64, epsilon::Float64, maxit::Int)
  fa = f(a)
  fb = f(b)

  for k = 1:maxit
    if (abs(fa) > abs(fb))
      a, b = b, a
      fa, fb = fb, fa
    end

    s = (b - a) / (fb - fa)
    b = a
    fb = fa
    a = a - (fa * s)
    fa = f(a)

    if (abs(b - a) < delta || abs(fa) < epsilon)
        return (a, fa, k, 0)
    end
  end
  return (a, fa, maxit, 1)  
end

end     # ending module