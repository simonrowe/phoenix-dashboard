package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        url '/api/ai/receive'
        headers {
            header('Authorization' : 'Basic dXNlcjpwYXNzd29yZA==')
            header('Content-Type', 'application/json;charset=UTF-8')
        }

        body '''
        [
            {
                "appGuidId" : "abc123",
                "instances" : 1,
                "foundationId" : "Foundation1"
            },
            {
                "appGuidId" : "def456",
                "instances" : 3,
                "foundationId" : "Foundation1"
            }
        ]
        '''
    }
    response {
        status 201
    }
}