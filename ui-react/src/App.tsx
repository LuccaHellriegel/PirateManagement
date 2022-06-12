import React, { FC } from "react";
import { QueryClient, QueryClientProvider } from "react-query";
import { Link, Outlet } from "react-router-dom";
import { ApiProvider } from "./ApiContext";
//TODO: find out how to disable the API method numbering

const queryClient = new QueryClient();

const App: FC = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <ApiProvider>
        <Link to="/treasures">Treasures</Link> | <Link to="/crews">Crews</Link>
        <Outlet />
      </ApiProvider>
    </QueryClientProvider>
  );
};

export default App;
