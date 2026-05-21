# Guance OpenAPI Java SDK

Generated Java SDK for Guance OpenAPI. The client groups public endpoints by module and automatically handles `DF-API-KEY`, query/body/path encoding, response envelopes, and `ApiException`.

The SDK currently uses only the JDK HTTP/runtime APIs, so it can be compiled directly without extra HTTP or JSON dependencies.

## Build locally

```bash
python3.11 tools/openapi_sdk/generate.py --target java
cd ../guance-sdk-java
mvn test
```

If Maven dependencies are unavailable in your environment, the generated sources can still be checked with `javac`:

```bash
find src/main/java -name "*.java" -print | sort | xargs javac -d /tmp/guance-openapi-sdk-java-classes
```

## Create a client

```java
Client client = new Client("https://openapi.example.com", "DF_API_KEY");
```

## List data

```java
ApiResponseEnvelope response = client.board.list(
    RequestOptions.withQuery(Map.of("pageIndex", 1, "pageSize", 10))
);
System.out.println(response.contentJson);
System.out.println(response.traceId);
```

## Path parameters

```java
RequestOptions req = RequestOptions.create();
req.path.put("dashboard_uuid", "dsbd_xxxx32");
ApiResponseEnvelope response = client.board.get(req);
```

## POST body

```java
RequestOptions req = RequestOptions.create();
req.bodyJson = "{\"name\":\"demo workspace\"}";
ApiResponseEnvelope response = client.workspace.modify(req);
```

## Query data with a typed content model

For the query-data endpoints, the SDK exposes both the raw envelope methods and typed content helpers:

```java
RequestOptions req = RequestOptions.create();
req.bodyJson = """
{
  "queries": [
    {
      "qtype": "dql",
      "query": {
        "q": "M::`cpu`:(avg(`usage_idle`))",
        "timeRange": [1708911106000, 1708912906999],
        "interval": 10,
        "maxPointCount": 720,
        "tz": "Asia/Shanghai"
      }
    }
  ],
  "fieldTagDescNeeded": false
}
""";

QueryDataContent content = client.queryData.queryDataV1Content(req);
for (QueryDataResult item : content.data) {
    for (QueryDataSeries series : item.series) {
        System.out.println(series.columns);
        System.out.println(series.values);
    }
}
```

The typed helper methods are:

```java
client.queryData.queryDataV1Content(req);
client.queryData.queryDataContent(req);
client.queryData.queryDataAsynchronousContent(req);
```

## Error handling

```java
try {
    client.board.list(RequestOptions.withQuery(Map.of("pageIndex", 1, "pageSize", 10)));
} catch (ApiException error) {
    System.out.println(error.httpStatus);
    System.out.println(error.errorCode);
    System.out.println(error.traceId);
    System.out.println(error.envelope.rawBody);
}
```
