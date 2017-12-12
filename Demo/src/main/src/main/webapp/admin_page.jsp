<html>
<head>
    <title>Admin Page</title>
</head>
<body>

<h1>Hello ${username}</h1>
<h1> ${message}</h1>
<h1 id = "agenda_names">${agenda_names}</h1>

<form action="/timer_page">
  <select id="agenda_form">
  	
  </select>
  <br><br>
  <input name = "agenda_names" type="submit">
</form>



<script>
var agenda_name_string = (document.getElementById("agenda_names").innerHTML).split(" ");


 for(var i = 0; i < agenda_name_string.length; i++)
{
var option = document.createElement("option");
 option.setAttribute('value',agenda_name_string[i]);
 option.innerHTML = agenda_name_string[i];
document.getElementById("agenda_form").appendChild(option);
} 
//  document.getElementById.("agenda_names").innerHTML = "";
</script>

<h2><a href="/timer_page">Stopwatch</h2>

<h2><a href="/createagendaitem">Create New Agenda</h2>

<h2><a href="/">Logout</h2>
</form>
</body>
</html>