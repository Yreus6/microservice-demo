projects="app-common app-order-service-api app-customer-service-api app-order-service app-customer-service app-api-gateway"

./gradlew clean

for project in $projects;
do
  if [[ "$project" =~ ^app-.*-service$ ]] || [ "$project" == "app-api-gateway" ]
  then
    ./gradlew :"$project":bootJar
  else
    ./gradlew :"$project":jar
  fi
done
