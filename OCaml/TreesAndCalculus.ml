let rec mapTree f (t: 'a tree) =
  match t with
  |Empty -> Empty
  |Node(left,middle,right) -> Node(mapTree f(left),f(middle), mapTree f(right))
;;



let rec halfint (f, (epsilon : float)) ((negValue : float), (posValue : float)) =
  let close x y = abs_float (x -. y) < epsilon in
  let midpoint = (negValue +. posValue)/.(2.0) in
  if close (f midpoint) 0.0 then midpoint 
  else if f(midpoint) < 0.0 then halfint(f, (epsilon : float)) ((midpoint : float), (posValue : float))
  else halfint(f, (epsilon : float)) ((negValue : float), (midpoint : float))
;;


let rec newton (f, (epsilon:float), (dx:float)) (guess:float) = 
  let close x y = abs_float (x -. y) < epsilon in
  let improve (guess:float) = guess -. (f(guess)/.deriv(f, dx)guess) in
  if close (f guess) 0.0 then guess
  else newton (f, (epsilon:float), (dx:float)) (improve (guess:float))

;;


let indIntegral (f, (dx:float)) =
  fun x -> integral((f: float -> float),(0.0:float),(x:float),(dx:float))
;;
