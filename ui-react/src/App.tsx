import { FC } from "react";
import { QueryClient, QueryClientProvider } from "react-query";
import { Outlet } from "react-router-dom";
import { ApiProvider } from "./ApiContext";
//TODO: find out how to disable the API method numbering

const queryClient = new QueryClient();

const App: FC = () => {
  return (
    <div style={{ backgroundColor: "lightgray", padding: "8px" }}>
      <QueryClientProvider client={queryClient}>
        <ApiProvider>
          <Outlet />
        </ApiProvider>
      </QueryClientProvider>
    </div>
  );
};

export default App;
