import express from 'express';
import genDatesRoutes from './genDates';
import swaggerUi from 'swagger-ui-express';
import swaggerDocument from '../swagger/swagger';
 
const router = express.Router();

router.get('/health-check', (req, res) => {
    res.status(200).json({
      status: 'ok'
    });
});

router.use('/api-docs', swaggerUi.serve);
router.get('/api-docs', swaggerUi.setup(swaggerDocument));
router.use('/genDates', genDatesRoutes);

export default router;