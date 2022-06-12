import { FC } from "react";
import { QueryClient, QueryClientProvider } from "react-query";
import { Outlet } from "react-router-dom";
import { ApiProvider } from "./ApiContext";
//TODO: find out how to disable the API method numbering

const queryClient = new QueryClient();

const App: FC = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <ApiProvider>
        <Outlet />
      </ApiProvider>
    </QueryClientProvider>
  );
};

export default App;
