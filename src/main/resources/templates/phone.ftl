<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>

    <h2 class="text-center">Ассортимент</h2>
    <h6> </h6>
    <table class="table">
        <thead>
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Количество</th>
            <th>Тип</th>
        </tr>
        </thead>
        <tbody>
        <#list items as item>
            <tr>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.price}</td>
                <td>${item.qty}</td>
                <td><#list item.type as type>${type}<#sep>, </#list></td>
                <#if isAdmin>
                <td>
                    <form method="post" action="/phone/deletePhone">
                        <input type="hidden" value="${item.id}" name="phoneId">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <button class="btn btn-danger" type="submit">      Удалить     </button>
                    </form></td>
                </#if>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
