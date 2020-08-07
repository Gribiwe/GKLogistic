package com.gk.logistic.util.network;

import com.gk.logistic.blocks.connector.TileEntityConnector;

import java.util.LinkedList;

public class Path {
    private TileEntityConnector startConnector;
    private TileEntityConnector endConnector;
    private Network network;
    public LinkedList<TileEntityConnector> nodes = new LinkedList<>();
    Path(Network network){
        this.network = network;
    }
    Path(TileEntityConnector start, TileEntityConnector end, Network network){
        this.startConnector = start;
        this.endConnector = end;
        this.network = network;
        nodes = network.getClosestPath(start, end);
    }

    public TileEntityConnector getStartConnector() {
        return startConnector;
    }

    public TileEntityConnector getEndConnector() {
        return endConnector;
    }
}
