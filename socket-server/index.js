let app = require('express')();
let http = require('http').Server(app);
let io = require('socket.io')(http);

io.on('connection', (socket) => {
	socket.on('disconnect', () => {
		io.emit('user-changed', {user: socket.nickname, event: 'left'});
	});

	socket.on('set-nickname', (nickname) => {
		socket.nickname = nickname;
		console.log('success');
		io.emit('user-changed', {user: nickname, event: 'joined'});
	});

	socket.on('message', (message) => {
        console.log('success');
		io.emit('message', {text: message.text, from: socket.nickname, created: new Date()});
	});

	socket.on('movement', (message) => {
		console.log(message);
		io.emit('movement', message);
	});

	socket.on('direction', (message) => {
		console.log(message);
		io.emit('direction', message);
	});
});

app.get('/', (req, res) => {
	res.send({test: 'hello'})
});

var port = 3001;

http.listen(port, () => {
	console.log('listening in http://localhost:' + port);
});
