document.addEventListener('DOMContentLoaded', function() {
    // 스킨 라디오 버튼들
    const skinMarket = document.getElementById('skin_market');
    const skinOptions = document.querySelectorAll('input[name="skin"]');

    const useReplyTrue = document.getElementById('useReply_true');
    const useReplyFalse = document.getElementById('useReply_false');
    const useCommentTrue = document.getElementById('useComment_true');
    const useCommentFalse = document.getElementById('useComment_false');
    const useEditorTrue = document.getElementById('useEditor_true');
    const useEditorFalse = document.getElementById('useEditor_false');
    const useCategory = document.getElementsByName('category');
    const listAccessTypeALL = document.getElementById('listAccessType_ALL');
    const listAccessTypeMEMBER = document.getElementById('listAccessType_MEMBER');
    const viewAccessTypeALL = document.getElementById('viewAccessType_ALL');
    const viewAccessTypeMEMBER = document.getElementById('viewAccessType_MEMBER');
    const writeAccessTypeALL = document.getElementById('writeAccessType_ALL');
    const writeAccessTypeMEMBER = document.getElementById('writeAccessType_MEMBER');

    // 스킨 선택 시 이벤트 리스너
    function toggleCommentOptions() {
        if (skinMarket.checked) {

            const confirmation = confirm("화개장터(market) 스킨을 선택하면 일부 기능 옵션이 비활성화됩니다. 계속하시겠습니까?");
            if (confirmation) {
                useReplyTrue.disabled = true;
                useReplyFalse.disabled = true;
                useReplyFalse.checked = true;

                useCommentTrue.disabled = true;
                useCommentFalse.disabled = true;
                useCommentFalse.checked = true;

                useEditorTrue.disabled = true;
                useEditorFalse.disabled = true;
                useEditorFalse.checked = true;

                useCategory.disabled = true;

                listAccessTypeALL.disabled = true;
                listAccessTypeMEMBER.checked = true;
                viewAccessTypeALL.disabled = true;
                viewAccessTypeMEMBER.checked = true;
                writeAccessTypeALL.disabled = true;
                writeAccessTypeMEMBER.checked = true;
            }else {
                // 취소 버튼을 누르면 다른 스킨 선택 상태로 복귀
                skinOptions.forEach(option => {
                    if (option.value !== 'market') {
                        option.checked = true;
                    }
                });
                toggleCommentOptions();
            }
        }else {
            // 다른 스킨 선택 시 댓글 사용 활성화
            useReplyTrue.disabled = false;
            useReplyFalse.disabled = false;
            useCommentTrue.disabled = false;
            useCommentFalse.disabled = false;
            useEditorTrue.disabled = false;
            useEditorFalse.disabled = false;
            listAccessTypeALL.disabled = false;
            viewAccessTypeALL.disabled = false;
            writeAccessTypeALL.disabled = false;
        }
    }

    // 모든 스킨 라디오 버튼에 이벤트 리스너 추가
    skinOptions.forEach(option => {
        option.addEventListener('change', toggleCommentOptions);
    });

    // 페이지 로드 시 초기 상태 설정
    toggleCommentOptions();
});
