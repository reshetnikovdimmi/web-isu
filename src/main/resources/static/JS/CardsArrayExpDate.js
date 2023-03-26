$(document).ready(function() {
let day = new Date("2020-04-11");
const json = '{"ProfileId": "12w562qrx", "PersonInfo": {"Name": "Василий Иванов","BirthDate": "12-09-1990","Citizenship": "Russian Federation"},"CardInfo": [{"CardNumber": "1234890456793333","CardName": "VISA CLASSIC","ExpDate": "30-02-2019"},{"CardNumber": "1234000145292133","CardName": "MASTERCARD GOLD","ExpDate": "21-05-2020"},{"CardNumber": "1234000145293333","CardName": "MIR КЛАССИЧЕСКАЯ","ExpDate": "02-12-2019"}]}';
getCardsArrayExpDate(json, day);

});

function getCardsArrayExpDate(json, date){
    const obj = JSON.parse(json);
    var arr = [];
    for (let i = 0; i < obj.CardInfo.length; i++) {
        let now = new Date(obj.CardInfo[i].ExpDate.split("-").reverse().join("-"));
        console.log(now)
        if (now.getTime() < date.getTime()) {
            arr.push(obj.CardInfo[i].CardNumber.substring(obj.CardInfo[i].CardNumber.length - 4));
        }
    }
    if(arr.length==0){

        return null;
    }

        return arr;
}