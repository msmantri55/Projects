let mysqrt (x:float) = 
  let rec helper(x, g) =
    if close(x, square(g)) then g
    else helper(x, ((g +. (x /. g)) /. (2.0)))
  in
  helper(x, 1.0);;
      
    


let cube_root (x:float) = 
  let rec helper(x, g) =
    if close(x, cube(g)) then g
    else helper(x, ((2.0 *. g) +. (x /. square(g))) /. 3.0)
  in
  helper(x, 1.0);;

let fast_exp (base, power) = 
  let rec rpe(base, power) =
    if base = 0 then 0 
    else 
    if power = 0 then 1
    else
    if (odd power) then 
      base * rpe (base, (power - 1))
    else
      let tmp = rpe (base, (power / 2))
      in
      tmp * tmp
  in
  rpe(base, power);;
            
  
                                         

                           

