<!DOCTYPE html>
<html>
<body>

<h1> <a href = "/"> </a>  Homepage</h1>
<p>Create a New Agenda Item with Agenda Name, Speaker Name, and time up to 60 minutes.</p>

<button onclick="myFunction()">New Speaker</button>

<script>
var speaker_count = 0;


var form_speaker = document.createElement("form");
form_speaker.setAttribute('method',"post");
form_speaker.setAttribute('action',"createagendaitem");
form_speaker.setAttribute('name', "speaker_form");
form_speaker.setAttribute('number_of_speakers', 0);

document.getElementsByTagName('body')[0].appendChild(form_speaker);


  var form_agenda_name = document.createElement("input");
  form_agenda_name.setAttribute('type', "text");
  form_agenda_name.setAttribute('value', "Agenda Name");
  form_agenda_name.setAttribute('name', "Agenda Name");
  
// form_speaker.appendChild(form_agenda_name);

var submit = document.createElement("input");
submit.setAttribute('type', "submit");
submit.setAttribute('value',"Submit");
form_speaker.appendChild(submit);
form_speaker.appendChild(document.createElement("br"));
form_speaker.appendChild(document.createElement("br"));
form_speaker.appendChild(form_agenda_name);
form_speaker.appendChild(document.createElement("br"));
// Number of speakers text
 var  number_of_speakers_text = document.createElement("input");
  number_of_speakers_text.setAttribute('value', "Number Of Speakers:");
  
 
    var  number_of_speakers = document.createElement("input");
   number_of_speakers.setAttribute('value', "0");
   number_of_speakers.setAttribute('name', "Number Of Speakers:")
   number_of_speakers.setAttribute('type', "text");
   number_of_speakers.setAttribute('id', 224);
  
  form_speaker.appendChild(number_of_speakers_text);
    form_speaker.appendChild(number_of_speakers);




form_speaker.appendChild(document.createElement("br"));
form_speaker.appendChild(document.createElement("br"));
// form_speaker.appendChild(form_agenda_name);




function myFunction() {
	speaker_count +=1;
 var speaker_name = document.createElement("input"); //input element, text
speaker_name.setAttribute('type',"text"); 
 speaker_name.setAttribute('name', "Speaker Name ".concat(speaker_count));
 speaker_name.setAttribute('value', "Speaker Name");
 speaker_name.setAttribute('count', speaker_count);

var form_time_in_minutes = document.createElement("input"); //input element, text
form_time_in_minutes.setAttribute('type',"number");
form_time_in_minutes.setAttribute('value', "0");
form_time_in_minutes.setAttribute('name',"Speaker Time ".concat(speaker_count));
form_time_in_minutes.setAttribute('min',"0");
form_time_in_minutes.setAttribute('max',"60");
form_time_in_minutes.setAttribute('count',speaker_count);



//var submit = document.createElement("input"); //input element, Submit button
//s.setAttribute('type',"submit");
//s.setAttribute('value',"Submit");
var br = document.createElement("br"); 

form_speaker.appendChild(speaker_name);
form_speaker.appendChild(form_time_in_minutes);
form_speaker.appendChild(br);

//form_speaker.appendChild(submit);

//and some more input elements here
//and dont forget to add a submit button

document.getElementsByTagName('body')[0].appendChild(form_speaker);

document.getElementById("224").setAttribute("value", speaker_count);


}
</script>

</body>
</html>
