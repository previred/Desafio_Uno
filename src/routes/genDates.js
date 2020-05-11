import express from 'express';
import genDatesController from '../controllers/genDates';

const router= express.Router();

router.route('/')
    .get(genDatesController.generateDates);

export default router;