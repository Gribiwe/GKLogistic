package com.gk.logistic.util.network;

import com.gk.logistic.blocks.connector.TileEntityConnector;

import java.util.*;

public class Network {
    public static List<Network> NETWORKS = new ArrayList<>();
    public List<TileEntityConnector> connectors = new ArrayList<>();
    private List<Path> paths = new ArrayList<>();

    public Network() {
        NETWORKS.add(this);
    }


    LinkedList<TileEntityConnector> getClosestPath(TileEntityConnector from, TileEntityConnector to) {

        if (!(from.isConnector && to.isConnector)) {
            return null;
        }

        LinkedList<TileEntityConnector> path = new LinkedList<>();
        Queue<TileEntityConnector> curr_path_queue = new ArrayDeque<>();
        Map<TileEntityConnector, LinkedList<TileEntityConnector>> pathsMap = new HashMap<>();
        curr_path_queue.offer(from);
        pathsMap.put(from, new LinkedList<>());
        pathsMap.get(from).addLast(from);

        boolean isSearching = true;
        while (isSearching) {
            TileEntityConnector current = curr_path_queue.poll();
            LinkedList<TileEntityConnector> curr_path = pathsMap.get(current);
            if (current.getPos().equals(to.getPos())) {
                    path = curr_path;
                    break;
            }
            List<TileEntityConnector> neighbors = current.getNeighbors();

            for (TileEntityConnector te:neighbors
                 ) {
                if (!te.getNetwork().equals(this)) {
                    te.setNetwork(this);
                }
                if (!pathsMap.containsKey(te)) {
                    curr_path_queue.offer(te);
                    pathsMap.put(te, new LinkedList<>(curr_path));
                    pathsMap.get(te).addLast(te);
                }
            }

            if (curr_path_queue.isEmpty()) {
                isSearching = false;
            }
        }

        return path;
    }

    public void addConnector (TileEntityConnector connector) {
        if (connector.isConnector) {
            for (TileEntityConnector cnctr:connectors
            ) {
                paths.add(new Path(connector, cnctr, this));
            }

            this.connectors.add(connector);
        }


    }

    public void removeConnector (TileEntityConnector connector) {
        if (connector.isConnector){
            paths.removeIf(t -> t.getStartConnector().getPos().equals(connector.getPos())
                    || t.getEndConnector().getPos().equals(connector.getPos()));
            this.connectors.remove(connector);
            if (connectors.isEmpty()) {
                NETWORKS.remove(this);
            }
        }
    }

    public List<Path> getPaths() {
        return paths;
    }

    public Path getClosestPathFor(TileEntityConnector connector) {
        int max = Integer.MAX_VALUE;
        Path minPath = new Path(this);
        for (Path path:paths
             ) {
            if (path.getStartConnector().getPos().equals(connector.getPos())
                    || path.getEndConnector().getPos().equals(connector.getPos())) {
                if (path.nodes.size() < max) {
                    minPath = path;
                    max = path.nodes.size();
                }
            }
        }
        return minPath;
    }
}
