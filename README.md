# SA_Assignment_01_OSGi

Business Scenario
Best Mobile is a system implemented in Java using the OSGi framework (Open Service Gateway Initiative) for the Best Smartphone Store that provides four services;
1. Customer management
2. Supplier management
3. Item management
4. Employee management.


It follows the microkernel architecture and deploys using the Eclipse Equinox. The system is created consisting of separate service bundles, with some acting as producers (publishers) and as consumers. There are four producers (publishers) and four consumers, each corresponding to one of these management areas: Customer, Supplier, Item, and Employee.

Data related to the POS service is stored in the local MySQL database. Connection to the database is implemented in the PhoneShopDB bundle and it is consumed by the services that need to connect to the database.

| Student ID  | Name                   | Contribution       |
|-------------|------------------------|--------------------|
| IT21377358  | Hanshani S.G.H.S       | Supplier Management|
| IT21377280  | Rajapaksha C. S.       | Employee Management|
| IT21378270  | Wimaladharma T. H. Y. B.| Item Management    |
| IT21355196  | Kalpajith K.L.S.       | Customer Management|


