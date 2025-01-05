function calculateDiscountedPrice() {
    let totalPrice = parseFloat(document.getElementById('totalPrice').value) || 0;
    let discountRate = parseFloat(document.getElementById('discountRate').value) || 0;
    let discountedPrice = totalPrice - (totalPrice * (discountRate / 100));
    document.getElementById('discountedPrice').value = discountedPrice.toFixed(2);
}