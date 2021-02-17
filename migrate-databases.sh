dbTasks="migrateOrderService migrateCustomerService"

for dbTask in $dbTasks;
do
  ./gradlew "$dbTask"
done
