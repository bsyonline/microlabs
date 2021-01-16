/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.alphax.service;

import com.rolex.alphax.grpc.ProductRequest;
import com.rolex.alphax.grpc.ProductResponse;
import com.rolex.alphax.grpc.ProductServiceGrpc;
import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Component;

/**
 * @author rolex
 * @since 2019
 */
@Component
public class ProductService {

    @GrpcClient("rpc-server")
    Channel serverChannel;

    public ProductResponse findProductByCustomerId(String customerId) {
        ProductServiceGrpc.ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(serverChannel);
        ProductResponse response = stub.findProductByClientId(ProductRequest.newBuilder().setUserId(customerId).build());
        return response;
    }

}
