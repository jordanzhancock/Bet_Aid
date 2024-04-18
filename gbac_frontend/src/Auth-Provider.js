import { Auth0Provider } from "@auth0/auth0-react";
import React from "react";
import { useNavigate } from "react-router-dom";
export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();
  const accessToken = localStorage.getItem("accessToken");
  const onRedirectCallback = (appState) => {
    navigate(appState?.returnTo || window.location.pathname);
  };
  if (!(accessToken && accessToken.length > 0)) {
    return null;
  }
  return (
    <Auth0Provider
      domain={''}
      clientId={''}
      redirectUri={window.location.origin}
      onRedirectCallback={onRedirectCallback}
    >
      {children}
    </Auth0Provider>
  );
};