#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<html>
<body>
	<a href="users">Get Users</a>
	<br />
	<br />
	<form action="putRequest/1" method="post">
		<input type="hidden" name="_method" value="PUT" /> <input
			type="submit" value="testRest PUT" />
	</form>
	<br />
	<br />
	<form action="deleteRequest/1" method="post">
		<input type="hidden" name="_method" value="DELETE" /> <input
			type="submit" value="testRest DELETE" />
	</form>
	<br />
	<br />
	<form action="postRequest" method="POST">
		<input type="submit" text="Post Request">
	</form>
	<br>
	<br>
	<a href="getRequest/1">Hello World!</a>
</body>
</html>
