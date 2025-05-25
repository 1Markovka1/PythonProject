package test;

import main.model.CellState;
import main.model.GridModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GridModelTest {
    @Test
    void testAddToShape() {
        GridModel model = new GridModel(10);
        model.addToShape(2, 2);
        assertEquals(CellState.SHAPE, model.getCellState(2, 2));
        assertEquals(1, model.getShapePoints().size());
    }
}