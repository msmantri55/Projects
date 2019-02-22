let makeProtectedAccount ((openingBalance: int), (password: string)) =
  let balance = ref openingBalance in
  let pass = ref password in
  let closed = ref false in
  fun ((s: string), (t: transaction)) -> 
    match t with 
    |Withdraw(m) ->  if (!pass <> s) then
          Printf.printf("Incorrect password.")
        else if (!closed) then Printf.printf("Account closed.")
        else  (if (!balance >= m)
               then
                 ((balance := !balance - m);
                  (Printf.printf "The new balance is: %i." !balance))
               else
                 print_string "Insufficient funds."
              )
    | Deposit(m) ->
        if (!pass <> s) then
          Printf.printf("Incorrect password.")
        else if (!closed) then Printf.printf("Account closed.")
        else 
          ((balance := !balance + m);
           (Printf.printf "The new balance is: %i." !balance))
    | CheckBalance -> if (!pass <> s) then
          Printf.printf("Incorrect password.")
        else if (!closed) then Printf.printf("Account closed.")
        else (Printf.printf "The balance is: %i." !balance)
    | ChangePassword(m) -> if (!pass <> s) then
          Printf.printf("Incorrect password.")
        else if (!closed) then Printf.printf("Account closed.")
        else ((pass := m); (Printf.printf "Password changed."))
    | Close -> if (!pass <> s) then 
          Printf.printf("Incorrect password.")
        else if (!closed) then Printf.printf("Account closed.")
        else ((closed := true);(Printf.printf("Account successfully closed.")));
;;
