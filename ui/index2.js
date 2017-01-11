function start(){
    databox=document.getElementById('databox');
    var button=document.getElementById('save');
    button.addEventListener('click', read, false);
}

function read(){
    var url="http://localhost:8080/stock-service/rest/stockService/stock";
    var request=new XMLHttpRequest();
    request.addEventListener('load',show,false);
    request.open("GET", url, true);
    request.send(null);
}

function show(e){
    itemList = JSON.parse(e.target.responseText);
    var htmlItemList = '';
    for (i=0;i<itemList.length;i++) {
        //databox.innerHTML='<div>' + res[0].name + ' <input type="button" name="add0" id="add0" value="+"> <input type="button" name="rem0" id="rem0" value="-"></div>' + 
        //              '<div>' + res[1].name + ' <input type="button" name="add1" id="add1" value="+"> <input type="button" name="rem1" id="rem1" value="-"></div>'
        htmlItemList=htmlItemList+
            '<div><input type="button" name="add0" id="add0" value="+" onclick="increaseAmount('+i+')"> <input type="button" name="rem0" id="rem0" value="-" onclick="decreaseAmount('+i+')"> - ' 
            + itemList[i].name + ' - Amount: '+ itemList[i].amount+'</div>'
    }
    databox.innerHTML=htmlItemList
}

function increaseAmount(i) {
    //console.log('-------------: ')
    console.log('-------------: '+itemList[i].name)
    
    var url="http://localhost:8080/stock-service/rest/stockService/item";
    var request=new XMLHttpRequest();
    request.addEventListener('load',itemUpdated,false);    
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/json");
    request.send(JSON.stringify(itemList[i]));    
}

function decreaseAmount(i) {
    console.log('-------------: '+itemList[i].name)
}

function itemUpdated() {
    console.log('item updated?')
    show();
}

var itemList;
window.addEventListener('load', start, false);