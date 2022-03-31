## How to run this application
- Ensure Docker is running
- Open project in Intellij
- Open ```docker-compose.yml``` in project root and boot up containers: ![image](https://user-images.githubusercontent.com/4882002/158885774-2864b41b-f5e0-4ae2-bfc4-5cee570d739f.png)
- Open ```BenchmarkRunner.java``` and press the play button: ![image](https://user-images.githubusercontent.com/4882002/161105149-623c49f3-b7a6-475f-ab1b-7480d2c42b87.png)


# API endpoints
The API exposes one endpoint, which takes a JSON body and saves it to your DB: ```POST /invoice```

*Example of the JSON body:*
```
{
    "totalAmount": 22,
    "companyName": "test",
    "comment": "this is a comment"
}
```

![image](https://user-images.githubusercontent.com/4882002/158885882-b9ecf057-5dde-4ecf-a472-8e91fcc8e00d.png)

# Development
**!!It is importan to create a new branch first before testing a JSON parser. The name of this branch is the name of the JSON parser!!**

Then you can edit the ```JsonParserBenchmark.java``` file to test your JSON parser. Make sure you are working on the correct branch.
Making a branch for every JSON parser we test, ensures it's easy to switch between different parsers without having to manually change code.

![image](https://user-images.githubusercontent.com/4882002/158886904-deebc9ea-152b-46f8-a0be-7ca8f15c4c8f.png)


[More info about how to work with JMH here.](https://www.baeldung.com/java-microbenchmark-harness)
