<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
ins {
    text-decoration: none;
}
</style>
<body onload="on_load()" >
<audio id="Audio">
  <source src="alarm.mp3" type="audio/mp3">
  

</audio>
<h1>${agenda_names}</h1>
<h1>

<p> Time Remaining: <ins id = "time left"> </ins> </p>
<p> Current Speaker: <ins id = "current speaker"> </ins> </p>

<br>
<br>


<button type="button" class="w3-btn w3-green" id="start_button" onclick="start_button()">START</button>  
<button type="button" class="w3-btn w3-red" id="stop_button" onclick="stop_button()">STOP</button>
<button type="button" class="w3-btn w3-gray" id="add_5_button" onclick="add_5_seconds()">ADD 5</button>
<button type="button" class="w3-btn w3-gray" id="add_10_button" onclick="add_10_seconds()">ADD 10</button>
<button  class="w3-btn w3-gray" id="add_30_button" onclick="add_20_seconds()">ADD 20</button>
 <button  class="w3-btn w3-gray" id="play_audio_button" onclick="sound_alarm()">Sound Alarm</button> 
<br>
<br>
<table id ="speaker table" style="width:100%">
  <caption> Click on speaker to set current speaker: </caption>
  <tr id = "speaker 1">
    <th>Speakers</th>
    <th>Time Remaining</th>
  </tr>

</table>

<script>
// create button objects
//  var start_button = document.createElement("start_button");
 // start_button.value = "START";
// var button_menu = document.getElementById("button_menu");
// button_menu.appendChild(start_button);




// construct from JSON object 
// this is a dummy agenda_item object
var current_speaker = {name:"agenda_item.speakers[0]", number:1};
var agenda_item = {speakers:["speaker_1", "speaker_2", "speaker_3"], times_in_ms:[66512, 40000, 20000], number_of_speakers:3};
var time_previous = null;
var time =  agenda_item.times_in_ms[0];
var time_switch = null;
// helper int div function
function intDiv(a,b)
{
var result = a/b;
if(result>=0)
return Math.floor(result);
else
return Math.ceil(result);
}

// format  time to 00:00:00
function time_format(time)
{
var minutes =  intDiv(time,60000);
var seconds =  intDiv(time%60000,1000); // (time % 60000) / 1000;
var hundreth_seconds = intDiv((time % 1000) ,  100);
 string_format = minutes + " " + ":" + " " + seconds + " " + ":" + " " + hundreth_seconds;
 return string_format; //return string_format; 
}

//countdown, update agenda item object, return time_formatted as string
function update()
{
if (time>0)
{
if (time_previous === null)
{
time_previous = new Date().getTime();
}
else
{
var time_difference = new Date().getTime() - time_previous;
time_previous = new Date().getTime();
time -= time_difference;
// agenda_item.times_in_ms
if(time <5000)
	{
	sound_alarm();
	}
document.getElementById("time left").innerHTML  = time_format(time);
agenda_item.times_in_ms[current_speaker.number - 1] = time;
document.getElementById("time row ".concat(current_speaker.number)).innerHTML = time;
}
}
}
function start_button()
{
time_switch = window.setInterval(update,1);
}

function stop_button()
{
window.clearInterval(time_switch);
time_previous = null;
}
function add_20_seconds()
{
time += 20000;
update();
}
function add_10_seconds()
{
time += 10000;
update();
}
function add_5_seconds()
{
time += 5000;
update();
}

function update_table()
{
var speaker_table = document.getElementById("speaker table");

// table_row_i = document.createElement("tr");
// var speaker_row_i = document.createElement("th");
// var time_row_i = document.createElement("th");
for ( i = 1; i <= agenda_item.number_of_speakers; i++)
{
// create ith table row and ith speaker and time remaining columns and speaker_button_i

var table_row_i = document.createElement("tr");
var time_row_i = document.createElement("td");
var speaker_row_i = document.createElement("td");
// var speaker_button_i = document.createElement("input");

// add id to ith table row speaker and time reamaining columns 
time_row_i.setAttribute('id', "time row ".concat(i));
speaker_row_i.setAttribute('id', "speaker row ".concat(i));
table_row_i.setAttribute('id', "table row ".concat(i));

// set speaker button attribute
var number = i;
speaker_row_i.setAttribute('onclick' , "setCurrentSpeaker(this.innerHTML," + i + ")");



//add table_row to table then add time column and speaker column to table row
speaker_table.appendChild(table_row_i);
table_row_i.appendChild(speaker_row_i);
table_row_i.appendChild(time_row_i);
// speaker_row_i.appendChild(speaker_button_i);

// add name of speaker_i to speaker_row_i
//  document.getElementById("speaker row ".concat(i)).innerHTML = random speaker// agenda_item.speakers[i-1];
// speaker_button_i.setAttribute('value',  agenda_item.speakers[i-1]);
// add time of speaker_i to time_row_i
// document.getElementById("time row ".concat(i)).innerHTML = random time // agenda_item.times_in_ms[i-1];
speaker_row_i.innerHTML = agenda_item.speakers[i-1];
time_row_i.innerHTML = agenda_item.times_in_ms[i-1];
}
}

function setCurrentSpeaker(p_name, p_number)
{
//click stop button
stop_button();
// set new current speaker name and number
current_speaker.name = p_name;
current_speaker.number = p_number;
// update Current speaker element
document.getElementById("current speaker").innerHTML = current_speaker.name;
time = agenda_item.times_in_ms[p_number-1];
// set time remaining 
document.getElementById("time left").innerHTML = time_format(agenda_item.times_in_ms[p_number-1]);
}

function on_load()
{
update_table();
setCurrentSpeaker("speaker_1", 1);
update();
}


function sound_alarm()
{
	document.getElementById("Audio").play();
}






// document.getElementById("time left").innerHTML  = time_format(agenda_item.times_in_ms[0]);
</script>
</h1>
</body>
</html>

