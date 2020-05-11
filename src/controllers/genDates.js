import axios from 'axios';
import moment from 'moment';
import { config } from 'dotenv';

config();
const generateDates = async(req, res) => {
    try {
        let dataServ = await axios.get(process.env.URL_API ? process.env.URL_API : 'http://127.0.0.1:8080/periodos/api');
        let { data } = dataServ;
        if ( Date.parse(data.fechaCreacion) && Date.parse(data.fechaFin) ) {
            const startDate = moment(data.fechaCreacion);
            const stopDate = moment(data.fechaFin);
            let dates = data.fechas;
            let fechasFaltantes = [];
            let currentDate = startDate;
            while (currentDate <= stopDate) {
                if (!dates.includes(moment(currentDate).format('YYYY-MM-DD'))) {
                    fechasFaltantes.push(moment(currentDate).format('YYYY-MM-DD'));
                }
                currentDate = moment(currentDate).add(1, 'month');
            }
            res.status(200).json({ data, fechasFaltantes });
        } else {
            res.status(400).json({ 'error': 'bad request' });
        }
        
      } catch (error) {
        res.status(500).json({"error": error.message});
      }
}

export default {
    generateDates
};