let areNeighbours ct1 ct2 (cht : chart) =
  let a = List.exists ( fun (x,y) -> (x = ct1) && (y = ct2) ) cht in 
  let b = List.exists ( fun (x,y) -> (y = ct1) && (x = ct2) ) cht in 
  a || b
;;



let canBeExtBy (col:colour) (ct: country) (ch : chart) =
  
  let a = List.for_all (fun x -> not(areNeighbours x ct ch)) col in 
  let b = not(List.mem ct col) in 

  a && b
;;



let rec extColouring (cht: chart) (colours : colouring) (cntry : country) =
  match colours with 
  |[] -> [[cntry]] 
  |h::t -> if (canBeExtBy h cntry cht) then (cntry::h)::t else h::extColouring cht t cntry
;;


let rec removeDuplicates lst =
  match lst with 
  |[] -> [] 
  |h::t -> h::removeDuplicates(List.filter (fun x -> not(h = x)) t) 
;;



let countriesInChart (cht: chart) =
  removeDuplicates(List.fold_left (fun inc (x,y) -> (x::y::inc)) [] cht) 
;;



let colourTheCountries (cht : chart) =
  List.fold_left (extColouring cht)  [] (countriesInChart cht)
;;



let rec insert comp (item: int) (list: rlist) = 
  let a = {data = item; next = ref None} in
  match !list with
  | None -> list := (Some a)
  | Some b ->
      if comp(a.data, b.data) 
      then (list := (Some a); a.next := (Some b))
      else
        insert comp item b.next
;;