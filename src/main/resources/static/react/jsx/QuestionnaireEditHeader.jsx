var QuestionnaireEditHeader = React.createClass({
    render: function() {
        return(
            <div className="questionnaire-edit--header">
                <div className="newsurvey">
                    <button className="newitem">Сохранить </button>
                </div>
                <textarea placeholder="Введите название анкеты" className="questionnaire-edit--questionnaire-title" name="title"></textarea>
            </div>
        );
    }
});