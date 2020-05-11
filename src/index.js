import http from 'http';
import app from './config/app';

const server = http.createServer(app);

server.listen(3000, () => {
    console.log(`Server running at http://127.0.0.1:3000/`);
});
  