var warshipObject = {
    noOfShips: 7,
    ship7: {locations: [],  locationsBeside: [], shipLength: 1, no: 7, class: "submarine1" }, 
    ship6: {locations: [],  locationsBeside: [], shipLength: 1, no: 6, class: "submarine2" },                
    ship5: {locations: [],  locationsBeside: [], shipLength: 2, no: 5, class: "destroyer1" },             
    ship4: {locations: [],  locationsBeside: [], shipLength: 2, no: 4, class: "destroyer2" },             
    ship3: {locations: [],  locationsBeside: [], shipLength: 3, no: 3, class: "cruiser"   },        
    ship2: {locations: [],  locationsBeside: [], shipLength: 4, no: 2, class: "battleship"},       
    ship1: {locations: [],  locationsBeside: [], shipLength: 5, no: 1, class: "AircraftCarrier" },       
    
    fieldsTakenByShips: [],
    fieldsBeside: [],
    fieldsTakenTotal: [],
    
    generateLocationsBeside: function (ship) {
        var locations = ship.locations;
        var locationsBeside = [];
       
/*if 1*/ if ((locations.indexOf(0) >= 0) ||  
            (locations.indexOf(10) >= 0) ||
            (locations.indexOf(20) >= 0) ||
            (locations.indexOf(30) >= 0) ||
            (locations.indexOf(40) >= 0) ||
            (locations.indexOf(50) >= 0) ||
            (locations.indexOf(60) >= 0) ||
            (locations.indexOf(70) >= 0) ||
            (locations.indexOf(80) >= 0) ||
            (locations.indexOf(90) >= 0)   ){

            for(y=0; y < ship.shipLength; y++){
                locationsBeside.push(locations[y] + 1);
                locationsBeside.push(locations[y] - 9);
                locationsBeside.push(locations[y] + 10);
                locationsBeside.push(locations[y] - 10);
                locationsBeside.push(locations[y] + 11);

            } // for end
    locationsBeside = this.digitChange(locationsBeside);
    eval('this.ship' + ship.no + ".locationsBeside = locationsBeside");
    return;
        }//if end
        
/*if 2 */ if ((locations.indexOf(99) >= 0) || 
            (locations.indexOf(9) >= 0) ||
            (locations.indexOf(19) >= 0) ||
            (locations.indexOf(29) >= 0) ||
            (locations.indexOf(39) >= 0) ||
            (locations.indexOf(49) >= 0) ||
            (locations.indexOf(59) >= 0) ||
            (locations.indexOf(69) >= 0) ||
            (locations.indexOf(79) >= 0) ||
            (locations.indexOf(89) >= 0)   ) {
            for(y=0; y < ship.shipLength; y++){
                locationsBeside.push(locations[y] - 1);
                locationsBeside.push(locations[y] + 9);
                locationsBeside.push(locations[y] + 10);
                locationsBeside.push(locations[y] - 10);
                locationsBeside.push(locations[y] - 11);
            } // for end
    locationsBeside = this.digitChange(locationsBeside);
    eval('this.ship' + ship.no + ".locationsBeside = locationsBeside");
    return;
        }//if end
        
/*else*/ else{    
            for(y=0; y < ship.shipLength; y++){
                locationsBeside.push(locations[y] + 1);
                locationsBeside.push(locations[y] - 1);
                locationsBeside.push(locations[y] + 9);
                locationsBeside.push(locations[y] - 9);
                locationsBeside.push(locations[y] + 10);
                locationsBeside.push(locations[y] - 10);
                locationsBeside.push(locations[y] + 11);
                locationsBeside.push(locations[y] - 11);
            } // for end
    locationsBeside = this.digitChange(locationsBeside);
    eval('this.ship' + ship.no + ".locationsBeside = locationsBeside");
    return;
        }//else end
}, //generate Locations Beside Function END
    
    digitChange: function(locationsBeside){

        var times = locationsBeside.length;
        for(w=0; w<times; w++){ /* change one digit to double-digit */
              if (locationsBeside.indexOf(0) >= 0){
                 var out =  locationsBeside.indexOf(0);
                locationsBeside.splice(out, 1);
                locationsBeside.push("00");
                locationsBeside.push("0");
                  } //if end
            }// for end
        
                for(w=0; w<times; w++){ /* change one digit to double-digit */
              if (locationsBeside.indexOf(1) >= 0){
                 var out =  locationsBeside.indexOf(0);
                locationsBeside.push("01");
                locationsBeside.push("1");
                  } //if end
            }// for end
        
                for(w=0; w<times; w++){ /* change one digit to double-digit */
              if (locationsBeside.indexOf(2) >= 0){
                 var out =  locationsBeside.indexOf(0);
                locationsBeside.push("02");
                locationsBeside.push("2");
                  } //if end
            }// for end
        
                for(w=0; w<times; w++){ /* change one digit to double-digit */
              if (locationsBeside.indexOf(3) >= 0){
                 var out =  locationsBeside.indexOf(0);
                locationsBeside.push("03");
                 locationsBeside.push("3");
                  } //if end
            }// for end
        
                for(w=0; w<times; w++){ /* change one digit to double-digit */
              if (locationsBeside.indexOf(4) >= 0){
                 var out =  locationsBeside.indexOf(0);
                locationsBeside.push("04");
                   locationsBeside.push("4");
                  } //if end
            }// for end
        
                for(w=0; w<times; w++){ /* change one digit to double-digit */
              if (locationsBeside.indexOf(5) >= 0){
                 var out =  locationsBeside.indexOf(0);
                locationsBeside.push("05");
                    locationsBeside.push("5");
                  } //if end
            }// for end
        
                for(w=0; w<times; w++){ /* change one digit to double-digit */
              if (locationsBeside.indexOf(6) >= 0){
                 var out =  locationsBeside.indexOf(0);
                locationsBeside.push("06");
                    locationsBeside.push("06");
                  } //if end
            }// for end
        
                for(w=0; w<times; w++){ /* change one digit to double-digit */
              if (locationsBeside.indexOf(7) >= 0){
                 var out =  locationsBeside.indexOf(0);
                locationsBeside.push("07");
                    locationsBeside.push("7");
                  } //if end
            }// for end
        
                for(w=0; w<times; w++){ /* change one digit to double-digit */
              if (locationsBeside.indexOf(8) >= 0){
                 var out =  locationsBeside.indexOf(0);
                locationsBeside.push("08");
                    locationsBeside.push("8");
                  } //if end
            }// for end
        
                for(w=0; w<times; w++){ /* change one digit to double-digit */
              if (locationsBeside.indexOf(9) >= 0){
                 var out =  locationsBeside.indexOf(0);
                locationsBeside.push("09");
                    locationsBeside.push("9");
                  } //if end
            }// for end
 
         return locationsBeside;
    }, // digit change function END

    
    
    
    setFieldsBeside: function (ship, coords) {

        
        for (q = 0; q < ship.shipLength; q++) {
        this.fieldsBeside.push(coords[q] + 1);
        this.fieldsBeside.push(coords[q] - 1);
        this.fieldsBeside.push(coords[q] + 9);
        this.fieldsBeside.push(coords[q] - 9);
        this.fieldsBeside.push(coords[q] + 10);
        this.fieldsBeside.push(coords[q] - 10);
        this.fieldsBeside.push(coords[q] + 11);
        this.fieldsBeside.push(coords[q] - 11);
        }
                     
        this.fieldsTakenByShips = this.fieldsTakenByShips.concat(coords);
        this.fieldsTakenTotal = this.fieldsTakenByShips.concat(this.fieldsBeside);
        return;
            
            
    
    
    }, // setFieldsBeside END
    
    collision: function (currentShip) { // COLLISION FUNCTION

        for (n = 0; n < this.fieldsTakenTotal.length; n++) {
            if (currentShip.indexOf(this.fieldsTakenTotal[n]) >= 0) {
                return true;
            } // if statement end
        } //for loop end
        return false;
    }, // collision Function End

    generateCoords: function (ship) { // GENERATE COORDS
        var jednosci;
        var dziesiatki;
        var liczba;
        var coords = [];
        var horizontalPosition = Math.floor(Math.random() * 2);
          
        if (horizontalPosition){

            do {
                jednosci =   Math.floor(Math.random() * (10-ship.shipLength));
                dziesiatki = Math.floor(Math.random() * 10) * 10;
                liczba = jednosci + dziesiatki;
                
                for(p=0; p < ship.shipLength; p++){
                coords[p] = liczba +p;
                }

            } while (this.collision(coords));

            this.setFieldsBeside(ship, coords);            
            return coords;
            
        } else {

            do {
                jednosci =   Math.floor(Math.random() * 10);
                dziesiatki = Math.floor(Math.random() * (10-ship.shipLength)) * 10;
                liczba = jednosci + dziesiatki;
                
                for(p=0; p < ship.shipLength; p++){
                coords[p] = liczba +(p*10);
                }

            } while (this.collision(coords));                        
            this.setFieldsBeside(ship, coords);                       
            return coords;
        }

    }, //generateCoords end

    generateWarships: function () {
        this.ship1.locations = this.generateCoords(this.ship1);
        this.ship2.locations = this.generateCoords(this.ship2);
        this.ship3.locations = this.generateCoords(this.ship3);
        this.ship4.locations = this.generateCoords(this.ship4);
        this.ship5.locations = this.generateCoords(this.ship5);
        this.ship6.locations = this.generateCoords(this.ship6);
        this.ship7.locations = this.generateCoords(this.ship7);

    }, //generateWarships end

}; // warshipObject end

var gameObject = {
    validator: new RegExp("^[a-jA-J]([0-9]|10)$"),  
    shotsMade: [],
    shotsNumber: 0,
    sunk: 0,
    
    
    formCheck: function(phraseToCheck){
        if(phraseToCheck.length <2 || phraseToCheck.length>3 || !(this.validator.test(phraseToCheck))  ){
          
              var log = document.getElementById('logScreen');
                log.setAttribute('class', 'warning');
                log.innerHTML = "坐标无效！ <br><br>请输入(A-J)(1-10)范围内的数值";
           return true;
        }
        
    },
    
    translate: function (phraseToCheck) {
     phraseToCheck = phraseToCheck.toUpperCase();
     var alphabet = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];
     var firstChar = phraseToCheck.charAt(0);
     var a = alphabet.indexOf(firstChar);
     var shotValue;

     if (phraseToCheck.length == 3) {
        shotValue = 90+a;
        
     } else {
         var secondChar = phraseToCheck.charAt(1) - 1;
         shotValue = "" + secondChar + a;
     }

     return shotValue;
 }, // translate function end


    shotBefore: function (check) {
        for (i = 0; i < this.shotsMade.length; i++) {
            if (check == this.shotsMade[i]) {
    
                var log = document.getElementById('logScreen');
                log.setAttribute('class', 'warning');
                log.innerHTML = "您已经炮击过这块地方了!指挥官！";
    

                return true;
            } // if end
        } // for end
    }, // shotBefore Function end

    isSunk: function (array, toHex) {
                if (array[0] == undefined) {
                gameObject.sunk++;
                    
                for(b=0; b<toHex.locationsBeside.length; b++){
                var hex = document.getElementById(toHex.locationsBeside[b]); 

                  hex.innerHTML = "x";
                } //for XXX end
                   

            var log = document.getElementById('logScreen');
                    log.setAttribute('class', 'info');
            log.innerHTML = "击沉敌舰！";
                    this.lineThrough(toHex); 
                    this.isGameOver();
                                     
    
            } // is Sunk if End
        }, // isSunk finction End
    
    lineThrough: function (shipArray) {
         var element = document.getElementById(shipArray.class);
             element.setAttribute('class', 'crossed');
  
        
     }, //fieldsBesideCross END
   
    

    
    isGameOver: function(){
        if(this.sunk == warshipObject.noOfShips){
            var log = document.getElementById('logScreen');
            log.setAttribute('class', 'info');
            log.innerHTML = "GAME OVER <BR> 恭喜！ <br><br> 你使用的弹药数为: " +this.shotsNumber + ".";
            return true;}
    }, // is GameOver end
    
    isGameOn: function(){
    if(this.sunk == warshipObject.noOfShips){
            var log = document.getElementById('logScreen');
        log.setAttribute('class', 'info');
            log.innerHTML = "GAME OVER <BR><br>通过刷新页面重新开始游戏！.";
            return true;
    }
} // is GameOn end
    
}; // gameObject End


// FUNCTION FIRE
function fire() {
    
       if (gameObject.isGameOn()){
          return;
       }
    var shot = document.getElementById("fireCoords").value;
    document.getElementById("fireCoords").value = "";
    
     if (gameObject.formCheck(shot)){
            return;
            }
    
    shot = gameObject.translate(shot);
       
        if (gameObject.shotBefore(shot)) {
        return;
    }
    gameObject.shotsNumber++;
    gameObject.shotsMade.push(shot); // zapisuje wartość strzału w tablicy shotsMade

    var ship1 = warshipObject.ship1.locations;
    var ship2 = warshipObject.ship2.locations;
    var ship3 = warshipObject.ship3.locations;
    var ship4 = warshipObject.ship4.locations;
    var ship5 = warshipObject.ship5.locations;
    var ship6 = warshipObject.ship6.locations;
    var ship7 = warshipObject.ship7.locations;
    
    var boardField = document.getElementById(shot);
       
   
    if (search(shot, ship1, ship2, ship3, ship4, ship5, ship6, ship7, boardField)) {

        boardField.setAttribute("class", "hit");
      
    } else {
                
        boardField.setAttribute("class", "miss");
        var log = document.getElementById('logScreen');
        log.setAttribute('class', 'info');
            log.innerHTML = "未击中！";
    }


    if(gameObject.isGameOver()){
        return;
    }
    
} // Function Fire End

// SEARCH FUNCTION 
function search(item, array1, array2, array3, array4, array5, array6, array7, boardField) {
  
            boardField.setAttribute("class", "hit");
            var log = document.getElementById('logScreen');
    log.setAttribute('class', 'info');
            log.innerHTML = "击中敌舰！";
    
    for (g = 1; g <= warshipObject.noOfShips; g++) {
     
        for (e = 0; e < 5; e++) {

            if (item == eval('array' + g)[e]) { // e.g. ship1[0]

            eval('warshipObject.ship' + g + '.locations').splice(e, 1); // usunięcie trafionego modułu statku z tablicy
                var locationsCopy   = eval('warshipObject.' + 'ship' + g+ '.locations');
                var shipCopy        = eval('warshipObject.' + 'ship' + g);

    gameObject.isSunk(locationsCopy, shipCopy); // sprawdza, czy pierwszy element w tablicy ship[] jest undefined na skutek trafienia
                return true;
            } //if end
        } 

    }// for noOfShips end
    return false;
} // Function Search End

// GAME START
window.onload = function () {
    warshipObject.generateWarships();
    
    warshipObject.generateLocationsBeside(warshipObject.ship1);     warshipObject.generateLocationsBeside(warshipObject.ship2); 
    warshipObject.generateLocationsBeside(warshipObject.ship3); 
    warshipObject.generateLocationsBeside(warshipObject.ship4); 
    warshipObject.generateLocationsBeside(warshipObject.ship5); 
    warshipObject.generateLocationsBeside(warshipObject.ship6); 
    warshipObject.generateLocationsBeside(warshipObject.ship7);

/*   alert(
     "Submarine1: " + warshipObject.ship7.locations     + ", "  +
     "Submarine2: " + warshipObject.ship6.locations      + ", "  +                   
     "Destroyer1: " + warshipObject.ship5.locations      + ", "  +                
     "Destroyer2: " + warshipObject.ship4.locations      + ", "  +               
     "Cruiser: "    + warshipObject.ship3.locations         + ", "  +           
     "Battleship: " + warshipObject.ship2.locations      + ", "  +        
     "Aircraft Carrier: " + warshipObject.ship1.locations + "."
        );//alert end  
  */      
};

 function enterHandler(){
       var keyPressed = event.keyCode || event.which;

        //if ENTER is pressed
        if(keyPressed==13){
            fire();
            keyPressed=null;
        } else        {
            return false;
        }
};