# MilkaApp
Java application written with use Spring Boot Freamwork integrated with Postgresql by Spring Data module.
<p>
Application contains few moudles:
  <p>
    <b>Models</b> - contain files coresponding to application entities
    <p>
     Month - entity responsible for creating new instances of months which contain relation with single days
      <p>
     Day - entity responsible for creating single day of month, contain relation with visit
        <p>
     Visit - entity responsible for creating new visit, contain relation with day
          <p>
     HairDres (Enum) - usable in creating new visit, contain name and time for visits
            <p>
     ModelsDTO - Data transfer object responsible for transfer data outside application   
              <p>
            <b>Repositories</b> - contain files responsible for communication with database records, use CrudReposity from Spring Boot Data
              <p>
                <b>Controllers</b> - this modul contain RESTController with endpoints for every request send to application. Support RESTApi
                <p>
                  <b>Services</b> - modul responsible for processing data delivered to application
                  <p>
                    This application only create restapi server side part. Frontend part is handled with REACT application which u can see in my repo.
                    <p>
                    <b>Run application</b>
                    <p>
                      For deploy application in your own environment, you have to use IDE which support java, personally prefer inellij idea ide. 
                      <p>
                      Additionaly you have to have installed PostgreSQL and create new database which can store data from application.
                        <p>
                          Database access u can configure in application.properties file.
                      <p>
                        After download  and properly configuration u can start application. 
                        <p>
                        For communication with endpoints i was using PostMan application for creating requests and receive responses
                  
