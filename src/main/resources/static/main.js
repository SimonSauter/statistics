var amountBtn = document.getElementById("post-amount");
var amountInput = document.getElementById("amount-input")
amountBtn.addEventListener("click", postAmount)

function makeTransactionPostRequest(url, amount) {
   var promiseObj = new Promise(function(resolve, reject){
      var xhr = new XMLHttpRequest();
      xhr.open("POST", url, true);
      xhr.setRequestHeader('Content-Type', 'application/json');
      xhr.send(amount);
      xhr.onreadystatechange = function(){
      if (xhr.readyState === 4){
         if (xhr.status === 200 || xhr.status === 201){
            var resp = xhr.responseText;
            resolve(resp);
         } else {
            reject(xhr.status);
         }
      } else {
         console.log("xhr processing going on");
      }
   }
 });
 return promiseObj;
}

function postAmount() {
    makeTransactionPostRequest("/api/transactions", constructTransaction()).then(resolve, errorHandler);
}
function resolve(text) {
    console.log(text);
}
function errorHandler(text) {
    console.log(text);
}
function constructTransaction() {
    var obj = {
        "amount" : amountInput.value,
        "timestampMillis" : 1522003105022
    }
    return JSON.stringify(obj);
}