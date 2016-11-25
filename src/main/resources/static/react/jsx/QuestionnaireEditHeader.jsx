var QuestionnaireEditHeader = React.createClass({
    render: function() {
        return(
            <div className="questionnaire-edit--header">
                <div>
                    <button className="btn btn-primary btn-lg">Сохранить </button>
                </div>
                <label>Название анкеты</label>
                <textarea className="questionnaire-edit--questionnaire-title" name="title"></textarea>
            </div>
        );
    }
});