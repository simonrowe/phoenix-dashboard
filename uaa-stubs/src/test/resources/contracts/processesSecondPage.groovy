org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/v3/processes?page=2&per_page=2'

    }
    response {
        status 200
        body("""{
           "pagination": {
              "last": "http://localhost:10001/v3/processes?page=2&per_page=2",
              "next": null
           },
           "resources" : [
             {
                 "guid": "def456",
                 "instances": 1,
                 "disk_in_mb": 64
             }
           ]
        }
        """)
        headers {
            header('Content-Type': 'application/json')
        }
    }
}