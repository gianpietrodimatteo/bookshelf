#!/usr/bin/env bash

# -------------------------------------------------------------
# Simple curl test script for the provided OpenAPI specification.
#
# 1. Update USER_ID, BOOK_ID, USER_BOOK_ID with IDs that exist in your system.
# 2. Make sure the server is running at http://localhost:8080 (or change BASE_URL).
# 3. Run:   chmod +x test_endpoints.sh
#           ./test_endpoints.sh
#
# -------------------------------------------------------------

BASE_URL="http://localhost:8080"

# ------------------------------------------------------------------
# Replace these with real IDs after you create users/books.
USER_ID=1          # e.g., the ID of an existing user
BOOK_ID=1          # e.g., the ID of an existing book
USER_BOOK_ID=1     # e.g., the ID of a user‑book relation
# ------------------------------------------------------------------

echo ""
echo "=== *** USERS *** ==="

echo ""
echo "=== POST /users ==="
curl -i -X POST "${BASE_URL}/users" \
     -H "Content-Type: application/json" \
     -d '{"username":"newuser","email":"new@example.com","fullName":"New User"}'

echo ""
echo "=== GET /users ==="
curl -i "${BASE_URL}/users" -H "Accept: application/json"

echo ""
echo "=== GET /users/${USER_ID} ==="
curl -i "${BASE_URL}/users/${USER_ID}" -H "Accept: application/json"

echo ""
echo "=== PUT /users/${USER_ID} ==="
curl -i -X PUT "${BASE_URL}/users/${USER_ID}" \
     -H "Content-Type: application/json" \
     -d '{"username":"updateduser","email":"updated@example.com","fullName":"Updated User"}'

echo ""
echo "=== *** BOOKS *** ==="

echo ""
echo "=== POST /books ==="
curl -i -X POST "${BASE_URL}/books" \
     -H "Content-Type: application/json" \
     -d '{"name":"New Book","author":"Author Name","year":2025,"genre":"Fantasy"}'

echo ""
echo "=== GET /books ==="
curl -i "${BASE_URL}/books" -H "Accept: application/json"

echo ""
echo "=== GET /books/${BOOK_ID} ==="
curl -i "${BASE_URL}/books/${BOOK_ID}" -H "Accept: application/json"

echo ""
echo "=== PUT /books/${BOOK_ID} ==="
curl -i -X PUT "${BASE_URL}/books/${BOOK_ID}" \
     -H "Content-Type: application/json" \
     -d '{"name":"Updated Book","author":"New Author","year":2024,"genre":"Non-fiction"}'

echo ""
echo "=== *** USER-BOOKS *** ==="

echo ""
echo "=== POST /user-books/user/${USER_ID}/book/${BOOK_ID} ==="
curl -i -X POST "${BASE_URL}/user-books/user/${USER_ID}/book/${BOOK_ID}"

echo ""
echo "=== GET /user-books?userId=${USER_ID} ==="
curl -i "${BASE_URL}/user-books?userId=${USER_ID}" -H "Accept: application/json"

echo ""
echo "=== PATCH /user-books/${USER_BOOK_ID} ==="
curl -i -X PATCH "${BASE_URL}/user-books/${USER_BOOK_ID}" \
     -H "Content-Type: application/json" \
     -d '{"status":"completed"}'

echo ""
echo "=== *** DELETES *** ==="

echo ""
echo "=== DELETE /user-books/${USER_BOOK_ID} ==="
curl -i -X DELETE "${BASE_URL}/user-books/${USER_BOOK_ID}"

echo ""
echo "=== DELETE /books/${BOOK_ID} ==="
curl -i -X DELETE "${BASE_URL}/books/${BOOK_ID}"

echo ""
echo "=== DELETE /users/${USER_ID} ==="
curl -i -X DELETE "${BASE_URL}/users/${USER_ID}"

