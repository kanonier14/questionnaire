var QuestionnaireEditHeader = React.createClass({
    render: function() {
        return(
            <div className="questionnaire-edit--header">
                <button>Сохранить </button>
                <label>Название анкеты</label>
                <textarea className="questionnaire-edit--questionnaire-title" name="title"></textarea>
            </div>
        );
    }
});