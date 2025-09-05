#!/usr/bin/env bash

# ------------------------------------------------------------------
#  Error‑response test script (bad request, not found, conflict)
#
#  Run with:   ./error_test.sh
#  Assumes the server is reachable at http://localhost:8080
#  Assumes the database is empty when this script starts.
# ------------------------------------------------------------------

BASE_URL="http://localhost:8080"

# --------------------------------------------------------------
# 1. Bad Request (400) scenarios
# --------------------------------------------------------------

echo ""
echo "=== *** BAD REQUEST TESTS *** ==="

# 1a. POST /users missing required field 'username'
echo ""
echo "--- POST /users (missing username) ---"
curl -i -X POST "$BASE_URL/users" \
     -H "Content-Type: application/json" \
     -d '{"email":"bad@example.com","fullName":"Bad User"}'

# 1b. PUT /users/1 with invalid email format
echo ""
echo "--- PUT /users/{id} (invalid email) ---"
curl -i -X PUT "$BASE_URL/users/1" \
     -H "Content-Type: application/json" \
     -d '{"username":"validuser","email":"not-an-email","fullName":"Valid User"}'

# 1c. POST /books missing required field 'author'
echo ""
echo "--- POST /books (missing author) ---"
curl -i -X POST "$BASE_URL/books" \
     -H "Content-Type: application/json" \
     -d '{"name":"Book Without Author","year":2023,"genre":"Fiction"}'

# 1d. PATCH /user-books/1 with empty body
echo ""
echo "--- PATCH /user-books/{id} (empty body) ---"
curl -i -X PATCH "$BASE_URL/user-books/1" \
     -H "Content-Type: application/json" \
     -d '{}'

# --------------------------------------------------------------
# 2. Not Found (404) scenarios
# --------------------------------------------------------------

echo ""
echo "=== *** NOT FOUND TESTS *** ==="

# 2a. GET non‑existent user
echo ""
echo "--- GET /users/9999 (non-existent) ---"
curl -i "$BASE_URL/users/9999"

# 2b. PUT non‑existent book
echo ""
echo "--- PUT /books/9999 (non-existent) ---"
curl -i -X PUT "$BASE_URL/books/9999" \
     -H "Content-Type: application/json" \
     -d '{"name":"Nonexistent","author":"No Author","year":2020,"genre":"None"}'

# 2c. DELETE non‑existent user‑book
echo ""
echo "--- DELETE /user-books/9999 (non-existent) ---"
curl -i -X DELETE "$BASE_URL/user-books/9999"

# 2d. GET with invalid id format (string instead of integer)
echo ""
echo "--- GET /users/abc (invalid id type) ---"
curl -i "$BASE_URL/users/abc"

# --------------------------------------------------------------
# 3. Conflict (409) scenarios
# --------------------------------------------------------------

echo ""
echo "=== *** CONFLICT TESTS *** ==="

# 3a. Create a user that will be used for duplicate test
echo ""
echo "--- POST /users (create baseline user) ---"
curl -s -X POST "$BASE_URL/users" \
     -H "Content-Type: application/json" \
     -d '{"username":"conflictuser","email":"conflict@example.com","fullName":"Conflict User"}'

# 3b. Attempt to create duplicate user with same username/email
echo ""
echo "--- POST /users (duplicate) ---"
curl -i -X POST "$BASE_URL/users" \
     -H "Content-Type: application/json" \
     -d '{"username":"conflictuser","email":"conflict@example.com","fullName":"Duplicate Conflict User"}'

# 3c. Create a book that will be used for duplicate test
echo ""
echo "--- POST /books (create baseline book) ---"
curl -s -X POST "$BASE_URL/books" \
     -H "Content-Type: application/json" \
     -d '{"name":"Conflict Book","author":"Author A","year":2022,"genre":"Sci-Fi"}'

# 3d. Create a user‑book relation (baseline)
echo ""
echo "--- POST /user-books/user/1/book/1 (create baseline) ---"
curl -s -X POST "$BASE_URL/user-books/user/1/book/1"

# 3e. Attempt to create duplicate user‑book with same user and book
echo ""
echo "--- POST /user-books/user/1/book/1 (duplicate) ---"
curl -i -X POST "$BASE_URL/user-books/user/1/book/1"

