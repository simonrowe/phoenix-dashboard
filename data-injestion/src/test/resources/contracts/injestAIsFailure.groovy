package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        url '/api/ai/receive'
        headers {
            header('Authorization' : 'Basic dXNlcjpwYXNzd29yZA==')
            header('Content-Type', 'application/json')
        }
        body '''
        [
            {
                "appGuidId" : "abc123",
                "instances" : 1,
                "foundationId" : null
            },
            {
                "appGuidId" : "def456",
                "instances" : null,
                "foundationId" : "Foundation2"
            }
        ]
        '''
    }
    response {
        status  500
    }
}