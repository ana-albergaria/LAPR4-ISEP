package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.base.warehousemanagement.repositories.PlantRepository;
import eapli.base.warehousemanagement.repositories.RowRepository;
import eapli.base.warehousemanagement.repositories.ShelfRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SetUpPlantController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final PlantRepository repositoryPlant = PersistenceContext.repositories().plants();
    private final AisleRepository repositoryAisle = PersistenceContext.repositories().aisles();
    private final RowRepository repositoryRow = PersistenceContext.repositories().rows();
    private final ShelfRepository repositoryShelf = PersistenceContext.repositories().shelfs();

    @SuppressWarnings("unchecked")
    public void setUpPlant(File json){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("employees.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray plantList = (JSONArray) obj;

            plantList.forEach( plant -> getElementsFromJSON( (JSONObject) plant ) );

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    private void getElementsFromJSON(JSONObject plant){
        WarehouseName name=null;
        Length length=null;
        Width width=null;
        SquareSize squareSize=null;
        Unit unit=null;
        Square beginSquare=null;
        Square endSquare=null;
        Square depthSquare=null;

        if(plant.get("Warehouse")!=null){
            JSONObject warehouseName = (JSONObject) plant.get("Warehouse");

            name = new WarehouseName(warehouseName.toJSONString());
        }
        if(plant.get("Length")!=null){
            JSONObject warehouseLength = (JSONObject) plant.get("Length");

            length = new Length(Integer.parseInt(warehouseLength.toJSONString()));
        }
        if (plant.get("Width")!=null){
            JSONObject warehouseWidth = (JSONObject) plant.get("Width");

            width = new Width(Integer.parseInt(warehouseWidth.toJSONString()));
        }
        if(plant.get("Square")!=null){
            JSONObject warehouseSquare = (JSONObject) plant.get("Square");

            squareSize = new SquareSize(Integer.parseInt(warehouseSquare.toJSONString()));
        }
        if(plant.get("Unit")!=null){
            JSONObject warehouseUnit = (JSONObject) plant.get("Unit");

            unit = new Unit(warehouseUnit.toJSONString());
        }
        final var newPlant= new PlantBuilder().
                hasName(name).
                hasLength(length).
                hasWidth(width).
                hasSquareSize(squareSize).
                hasUnit(unit).
                build();
        repositoryPlant.save(newPlant);

        if(plant.get("Aisles")!=null){
            JSONObject warehouseAisle = (JSONObject) plant.get("Aisles");

            Long id = (Long) warehouseAisle.get("Id");
            if(plant.get("begin")!=null){
                JSONObject warehouseBegin = (JSONObject) warehouseAisle.get("begin");

                int lsquare = (int) warehouseBegin.get("lsquare");
                int wsquare = (int) warehouseBegin.get("wsquare");

                beginSquare = new Square(lsquare, wsquare);
            }
            if(plant.get("end")!=null){
                JSONObject warehouseEnd = (JSONObject) warehouseAisle.get("end");

                int lsquare = (int) warehouseEnd.get("lsquare");
                int wsquare = (int) warehouseEnd.get("wsquare");

                endSquare = new Square(lsquare, wsquare);
            }
            if(plant.get("depth")!=null){
                JSONObject warehouseDepth = (JSONObject) warehouseAisle.get("depth");

                int lsquare = (int) warehouseDepth.get("lsquare");
                int wsquare = (int) warehouseDepth.get("wsquare");

                depthSquare = new Square(lsquare, wsquare);
            }
            JSONObject fileAccessibility = (JSONObject) warehouseAisle.get("accessibility");
            Accessibility accessibility = new Accessibility(fileAccessibility.toJSONString());
            final var newAisle = new AisleBuilder()
                    .hasID(id).hasBegin(beginSquare)
                    .hasEnd(endSquare)
                    .hasDepth(depthSquare)
                    .hasAccessibility(accessibility)
                    .build();
            repositoryAisle.save(newAisle);

            if (plant.get("rows")!=null){
                JSONObject warehouseRow = (JSONObject) plant.get("rows");

                Long idRow = (Long) warehouseRow.get("Id");

                if(plant.get("begin")!=null){
                    JSONObject warehouseBegin = (JSONObject) warehouseRow.get("begin");

                    int lsquare = (int) warehouseBegin.get("lsquare");
                    int wsquare = (int) warehouseBegin.get("wsquare");

                    beginSquare = new Square(lsquare, wsquare);
                }
                if(plant.get("end")!=null){
                    JSONObject warehouseEnd = (JSONObject) warehouseRow.get("end");

                    int lsquare = (int) warehouseEnd.get("lsquare");
                    int wsquare = (int) warehouseEnd.get("wsquare");

                    endSquare = new Square(lsquare, wsquare);
                }
                final var newRow = new RowBuilder()
                        .hasID(idRow)
                        .hasBegin(beginSquare)
                        .hasEnd(endSquare)
                        .hasAisle(newAisle)
                        .build();
                repositoryRow.save(newRow);

                int numShelfs = (int) warehouseRow.get("shelves");
                for (int i=0; i<numShelfs; i++){
                    final var newShelf = new ShelfBuilder().hasRow(newRow).build();
                    repositoryShelf.save(newShelf);
                }
            }
        }
        if (plant.get("AGVDocks")!=null){
            //continuar

        }
    }
}
