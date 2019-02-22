let rec pairlists (l1, l2) = 
  match (l1, l2) with
  | [], [] -> []
  | h1::t1, h2::t2 -> (h1, h2) :: pairlists(t1, t2)
;;

let wmean weights data = 
  let tmp = pairlists(weights, data) in
  let tmp2 =  List.map(fun(h1, h2) -> h1 *. h2) tmp in 
  sumlist(tmp2) /. sumlist(weights) 
;; 



let rec memberof (n, l) =
  match l with
  | [] -> false 
  | (h::t) -> if (h = n) then true else memberof(n, t) 
;;

let rec remove (item, lst) = 
  if memberof(item, lst) then
    match lst with
    | [] -> [] 
    | h::t -> if (h = item) then remove(item, t) else h::remove(item, t) 
  else lst
;;
    


let find_max l =
  let rec helper (l, max) =
    match l with 
    |h::t -> if h > max then helper(t, h) else helper(t, max)
    |_ -> max
  in helper(l, List.hd l)
;;



let rec selsort l = 
  match l with
  |[] -> []
  |l -> find_max(l)::selsort(remove(find_max(l), l))
;;
