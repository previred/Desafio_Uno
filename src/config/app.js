import express from 'express';
import bodyParser from 'body-parser';
import routes from '../routes';
import path from 'path';

const app = express();

// Configure bodyParser
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true })); 

app.use('/api', routes);
let img = path.join(__dirname, '../images');

app.use('/resources', express.static(img));
export default app;