let rec common twolists = 
  match twolists with 
  |[], [] -> []
  |[], h2::t2 -> []
  |h1::t1, [] -> []
  |h1::t1, l2 -> if memberof(h1, l2) then h1::common(t1, remove(h1, l2)) else common(t1, l2)
;;



let rec split l = 
  match l with
  |[] -> ([], []) 
  |[n] -> ([n], [])
  |h1::h2::t -> let (l1, l2) = split(t) in (h1::l1, h2::l2)
;;



let rec merge twolists =
  match twolists with
  |[], [] -> []
  |[], h2::t2 -> h2::t2
  |h1::t1, [] -> h1::t1
  |h1::t1, h2::t2 -> if h1 >= h2 then h2::merge(h1::t1, t2) else h1::merge(h2::t2, t1)
;;


let rec mergesort l =
  match split(l) with
  |[], [] -> []
  |[], h2::t2 -> h2::t2
  |h1::t1, [] -> h1::t1
  |l1, l2 -> merge(mergesort(l1), mergesort(l2))
;;
