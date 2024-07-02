# Stage 1: Build React App
FROM node:18-alpine AS build

# Add a bash shell to the image
RUN apk add --no-cache bash

# Set working directory
WORKDIR /app

# Add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# Define build arguments
ARG BACKEND_URL="http://localhost:8080"
ENV REACT_APP_BACKEND_URL=$BACKEND_URL
ARG STORAGE_URL="https://fly.storage.tigris.dev/businesstrips-storage/"
ENV STORAGE_URL=$STORAGE_URL

# Install app dependencies
COPY package.json ./
RUN yarn install --silent

# Add all to app
COPY ./src/ ./src/
COPY ./public/ ./public/

# Build the app
RUN yarn build

# Stage 2: Serve React App
FROM nginx:stable-alpine

# Copy build artifacts from Stage 1
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
