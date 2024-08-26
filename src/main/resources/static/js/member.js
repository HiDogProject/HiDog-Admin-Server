function showPostDetails(postId) {
    // Ajax 요청으로 게시물의 상세 정보를 가져옵니다
    fetch(`/posts/${postId}`)
        .then(response => response.text())
        .then(data => {
            document.getElementById('modalBody').innerHTML = data;
            document.getElementById('postModal').style.display = 'block';
        });
}

function closeModal() {
    document.getElementById('postModal').style.display = 'none';
}

window.onclick = function(event) {
    if (event.target === document.getElementById('postModal')) {
        closeModal();
    }
};