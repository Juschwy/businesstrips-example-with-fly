const API_URL = (process.env.REACT_APP_BACKEND_URL ?? "http://localhost:8080") + "/v1/trips"

export async function getBusinessTrips() {
  const response = await fetch(API_URL);
  if (response.ok) return response.json();
  throw response;
}
export async function getWishlistItems() {
  // fetch carts from api
  const response = await fetch(API_URL);
  if (response.ok) return response.json();
  throw response;
}
